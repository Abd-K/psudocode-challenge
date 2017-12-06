//This class represents a user in a simple dating app, with a set of photos, a lead image and an age
class UserProfile {
    String userName;
    Date dateOfBirth;
    String[] imageUrls;
    int mainImageIndex;

    public UserProfile(String userName, String dateOfBirth) {
        userName = userName;
        dateOfBirth = Date.parse("ddMMyyyy",dateOfBirth);
        imageUrls = new String[0];
    }
    
    void addImageUrl(String newUrl) {
        String[] tempUrlHolder = imageUrls;
        imageUrls = new String[tempUrlHolder.length + 1];
        for(int i = 0; i < tempUrlHolder.length; i++)
        {
            imageUrls[i] = tempUrlHolder[i];
        }
        imageUrls[tempUrlHolder.length] = newUrl;
    }

    void clearImages() {
        imageUrls = new String[0];
    }

    void setMainImage(int index) {
        mainImageIndex = index;
    }

    String getMainImage() {
        return imageUrls[mainImageIndex];
    }

    int getAge() {
        //Annoyingly, Date.minus returns the difference in days.
        int ageInDays = new Date().minus(dateOfBirth);
        return ageInDays/365;
    }
}