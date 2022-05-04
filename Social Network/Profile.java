import java.util.ArrayList;
/**
 * Profile class creates a profile object.
 */
public class Profile {
    /**
     * Is the name of user.
     * @param name name of user.
     */
    String name;
    /**
     * Profile status.
     * @param status is the status of profile.
     */
    String status;
    /**
     * List of friends.
     * @param friendProfiles is a list of Profiles friends.
     */
    ArrayList<Profile> friendProfiles;
    /**
     * Profile constructor with no parameters initializes everything as empty.
     */
    public Profile(){
        name = "";
        status = "";
        friendProfiles = new ArrayList<>();
    }
    /**
     * Profile constructor with name status and friendProfiles parameters.
     * @param name name of profile being created.
     * @param status status of profile being created.
     * @param friendProfiles list of friends that profile has.
     */
    public Profile(String name, String status, ArrayList<Profile> friendProfiles){
        this.name = name;
        this.status = status;
        this.friendProfiles = friendProfiles;
    }
    /**
     * Profile constuctor initializes profile with only name and stauts, but no friends.
     * @param name name of profile being created.
     * @param status status of profile being created.
     */
    public Profile(String name, String status){
        this.name = name;
        this.status = status;
        friendProfiles = new ArrayList<>();
    }

    /**
     * setName of profile.
     * @param firstName first name of profile.
     * @param lastName last name of profile.
     */
    public void setName(String firstName, String lastName){
        name = firstName + " " + lastName;
    }
    /**
     * getName gets the name of profile.
     * @return String value of profile name.
     */
    public String getName(){
        return name;
    }
    /**
     * setStatus sets the status of profile.
     * @param status status to be set.
     */
    public void setStatus(String status){
        this.status = status;
    }
    /**
     * getStatus returns status of profile.
     * @return String value of status.
     */
    public String getStatus(){
        return status;
    }
    /**
     * toString returns Profile as readable String.
     * @return String value of profile.
     */
    public String toString(){
        return "Name: " + name + "\n\tStatus: " + status + "\n\tNumber of friend profiles: " + friendProfiles.size() + "\n";
    }
    /**
     * Display prints and displays the profile itsself and all of the profiles friends.
     */
    public void display(){
        String friends = "";
        for(int i = 0; i < friendProfiles.size(); i++){
            friends += "\n\t" + friendProfiles.get(i).getName();    
        }
        System.out.println(this + "Friends:" + friends + "\n");
    }
    /**
     * returns the friendProfiles list of current profile.
     * @return ArrayList of profiles.
     */
    public ArrayList<Profile> getFriendProfiles(){
        return friendProfiles;
    }
    /**
     * addFriend adds a profile to friendProfiles list.
     * @param user the profile to be added as a friend.
     */
    public void addFriend(Profile user){
        if(friendProfiles.contains(user)){
            System.out.println(user.getName() + " is already a friend");
        }
        else{
            friendProfiles.add(user);
        }
    }
    /**
     * unFriend removes a profile from the friendProfile list.
     * @param user profile to be removed from list.
     * @return true if operation is successful, false if user being unfriended DNE.
     */
    public boolean unFriend(Profile user){
        if(friendProfiles.contains(user)){
            friendProfiles.remove(user);
            return true;
        }
        else{
            return false;
        }
    }
}