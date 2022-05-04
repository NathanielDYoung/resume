import java.util.ArrayList;
import java.util.Stack;
/**
 * MasonConnect is a graph that represents a network of Profiles.
 */
public class MasonConnect {
    /**
     * Graph of profiles.
     * @param masonConnect is the graph of Profiles that represents MasonConnect
     */
    Graph<Profile> masonConnect;
    /**
     * MasonConnect constructor creates and initializes an empty graph.
     */
    public MasonConnect(){
        masonConnect = new Graph<>();
    } 
    /**
     * addUser adds a user to masonconnect.
     * @param p is the profile to be added.
     */
    public void addUser(Profile p){
        if(exists(p)){
            System.out.println("No!");
        }
        else{
            masonConnect.addVertex(p);
            for(Profile f : p.getFriendProfiles()){
                if(exists(f)){  
                    createFriendship(p, f);
                    f.addFriend(p);
                }
                else{
                    masonConnect.addVertex(f);
                    createFriendship(p, f);
                    f.addFriend(p);
                }
            }
        }
    }
    /**
     * removeUser removes a user from masonconnect.
     * @param p profile to be removed.
     * @return Profile that was removed.
     */
    public Profile removeUser(Profile p){
        VertexInterface<Profile> profile = masonConnect.removeVertex(p);
        if(profile == null){
            return null;
        }
        else{
            return p;
        }
    }
    /**
     * createFriendShip creates a friendship between two users a and b.
     * @param a user one.
     * @param b user two.
     * @return true if friendship is created successfully, false if otherwise for any reason.
     */
    public boolean createFriendship(Profile a, Profile b){
        if(masonConnect.addEdge(a, b)){
            a.addFriend(b);
            b.addFriend(a);
        }
        return masonConnect.addEdge(a, b);
    }
    /**
     * removeFriendship unfriends two users a and b.
     * @param a user one.
     * @param b user two.
     * @return true if friendship is disbanded successfully, false if otherwise.
     */
    public boolean removeFriendship(Profile a, Profile b){
        if(masonConnect.removeEdge(a, b)){
            masonConnect.removeEdge(a, b);
            masonConnect.removeEdge(b, a);
            a.unFriend(b);
            b.unFriend(a);
            return true;
        }
        return false;
    }
    /**
     * hasFriendship determines if a friendship exists between two users a and b.
     * @param a user one.
     * @param b user two.
     * @return true if friendship exists, false otherwise.
     */
    public boolean hasFriendship(Profile a, Profile b){
        if(a.friendProfiles.contains(b)){
            return true;
        }
        return false;
    }
    /**
     * traverse graph from specified start point to display all connected profiles.
     * @param startPoint the origin point of traversal.
     */
    public void traverse(Profile startPoint){
        for(Profile p : masonConnect.getBreadthFirstTraversal(startPoint)){
            p.display();
        }
    }
    /**
     * exists determines if profile exists or not in MasonConnect.
     * @param user profile to be checked for.
     * @return true if user is in MasonConnect, false if otherwise.
     */
    public boolean exists(Profile user){
        for(VertexInterface<Profile> p : masonConnect.getVertices()){
            if(p.getLabel().equals(user)){
                return true;
            }
        }
        return false;
    }
    /**
     * friendSuggestions returns friends of friends of specified user.
     * @param user user to get suggestions for.
     * @return List of profiles to suggest as friends or null if user DNE.
     */
    public ArrayList<Profile> friendSuggestion(Profile user){
        ArrayList<Profile> userFriends = user.getFriendProfiles();
        ArrayList<Profile> suggestions = new ArrayList<>();
        if(exists(user)){
            for(Profile f : userFriends){
                for(Profile fof : f.getFriendProfiles()){
                    if(!userFriends.contains(fof) && fof != f && fof != user){
                        suggestions.add(fof);
                    }
                }
            }
        }
        else{
            return null;
        }
        return suggestions;
    }
    /**
     * friendshipDistance determines number of friends between two specified users a and b.
     * @param a user one.
     * @param b user two.
     * @return number of profiles it takes to get from a to b.
     */
    public int friendshipDistance(Profile a, Profile b){
        Stack<Profile> path = new Stack<>();
        return masonConnect.getShortestPath(a, b, path);
    }
}
