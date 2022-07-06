package nfl.main.service;

import org.springframework.stereotype.Repository;

@Repository
public class ServiceInterfaceImpl implements ServiceInterface {
	
    
	@Override
	public String before(String value, String a) {
        // Return substring containing all characters before a string.
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        return value.substring(0, posA);
    }

	@Override
    public String after(String value, String a) {
        // Returns a substring containing all characters after a string.
        int posA = value.lastIndexOf(a);
        if (posA == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= value.length()) {
            return "";
        }
        return value.substring(adjustedPosA);
    }

	@Override
	public String getURLNameWithSpace(String name) {
		String after = name.replaceAll("-"," ");
		return after;
	}

	@Override
	public String getTitleURL(String title) {
		String after = title.replaceAll("[\\-| |\\.]+", "-").toLowerCase();
		return after;
	}

    
}
