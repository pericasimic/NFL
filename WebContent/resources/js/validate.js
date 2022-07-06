
// ------------------------------------------------------- //
// Validate Front Comment Form
// ------------------------------------------------------ //

function validate(){
    var f=document.getElementById("form");
    validateAlias(f);
    validateEmail(f);
    validateText(f);

}

function validateAlias(form){
    var error=document.getElementById("aliasError");

    var alias=form["alias"].value;
	error.innerHTML="";
    if( alias==null || alias==""){
        error.innerHTML="Input Your Alias";
        event.preventDefault();
    }

    else if(alias.length<3 || alias.length>45){
        error.innerHTML="min 3, max 45 characters";
        event.preventDefault();
    }
    
	else if(!isNaN(alias)){
		error.innerHTML="Name Can Not be a number";
		event.preventDefault();
	}

    }
    
function validateEmail(form){
     var error=document.getElementById("emailError");

     var email=form["email"].value;
     error.innerHTML="";
     var regx = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
     if( email==null || email==""){
      error.innerHTML="Input Your Email";
      event.preventDefault();
    }
    
    else if(!email.match(regx)){
    error.innerHTML="Invalid Email";
    event.preventDefault();
    
 	}
   
 }
 
function validateText(form){
 	var error=document.getElementById("textError");

    var text=form["text"].value;
	error.innerHTML="";
    if( text==null || text==""){
        error.innerHTML="Input Your Comment";
        event.preventDefault();
    }

    else if(!isNaN(text)){
        error.innerHTML="Comment Can Not be a number";
        event.preventDefault();
    }

    else if(text.length<10 || text.length>300){
        error.innerHTML="min 10, max 300 characters";
        event.preventDefault();
    }       

}

// ------------------------------------------------------- //
// Validate Change Password
// ------------------------------------------------------ //

function verifyPassword() {  
	
  	var oldPwd = document.getElementById("oldPwd").value;  
  	var newPwd = document.getElementById("newPwd").value;
  	var conPwd = document.getElementById("conPwd").value;
  	
  	//check empty password fields  
  	if(oldPwd == "" || newPwd == "" || conPwd == "") {  
     document.getElementById("message").innerHTML = "**Fill the fields please!";  
     return false;  
  	}  
   
	//maximum length of password validation  
 	 if(oldPwd.length > 15 || newPwd.length > 15 || conPwd.length > 15) {  
     	document.getElementById("message").innerHTML = "**Password length must not exceed 15 characters";  
     	return false;  
  	} 
  	
  	//check match validation  
 	 if(newPwd.length != conPwd.length) {
     	document.getElementById("message").innerHTML = "**The password does not match";  
     	return false;  
  	} 
  	
  	//minimum length of password validation  
 	 if(newPwd.length < 8) {  
     	document.getElementById("message").innerHTML = "**Password must not be less than 8 characters";  
     	return false;  
  	}   	
  	
}  