package comp74.week2.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.springframework.boot.context.config.Profiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Model {

    HashMap<Integer, Profile> profiles;
    HashMap<Integer, Posting> postings;
    HashMap<String, Profile> userMap;

    private static Integer nextProfileId = 100;
    private static Integer nextPostingId = 100;

    public Model() {
        super();
        profiles = new HashMap<>();
        userMap = new HashMap<>();
        postings = new HashMap<>();
    }

    // POST
    // public Posting addPosting(Profile profile, Posting posting) {
    //     posting.setPostingId(nextPostingId++);
    //     posting.setDate(LocalDateTime.now());
    //     posting.setUserName(profile.getUserName());
    //     postings.put(posting.getPostingId(), posting);
    //     profile.addPosting(posting);
    //     return posting;
    // }

    // Remove the duplicate method addProfile(Profile)
    // public Profile addProfile(Profile profile) {
    //     Profile newProfile = null;
    //     if (userMap.get(profile.getUserName()) == null) {
    //         newProfile = profile;
    //         newProfile.setProfileId(nextProfileId++);
    //         profiles.put(newProfile.getProfileId(), newProfile);
    //         userMap.put(newProfile.getUserName(), newProfile);
    //     }
    //     return newProfile;
    // }

    // GET ALL

    public List<Profile> getProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public List<Posting> getPostings() {
        return new ArrayList<>(postings.values());
    }

    // GET BY ???

    public Profile getProfileById(Integer id) {
        Profile profile = profiles.get(id);
        return profile;
    }
    

    public List<Profile> getProfilesByUserName(String userName) {
        Profile profile = userMap.get(userName);
        return new ArrayList<>(Arrays.asList(profile));
    }

    public ResponseEntity<Posting> addPosting(Profile profile, Posting posting) {
        posting.setPostingId(nextPostingId++);
        posting.setDate(LocalDateTime.now());
        posting.setUserName(profile.getUserName());
        postings.put(posting.getPostingId(), posting);
        profile.addPosting(posting);

        return new ResponseEntity<>(posting, HttpStatus.CREATED);
    }

    public ResponseEntity<Profile> addProfile(Profile profile) {
        Profile newProfile = null;
        if (userMap.get(profile.getUserName()) == null) {
            newProfile = profile;
            newProfile.setProfileId(nextProfileId++);
            profiles.put(newProfile.getProfileId(), newProfile);
            userMap.put(newProfile.getUserName(), newProfile);
        }
        return new ResponseEntity<>(newProfile, HttpStatus.CREATED);
    }

    public boolean deletePosting(Profile profile, Integer postingid)
    {
        List<Posting> postings = profile.getPostings();
        for (Posting posting : postings) {
            if(posting.getPostingId().equals(postingid))
            {
                System.out.println("here3");
                postings.remove(posting);
                return  true;
            }
        } 
        return false;
    }
    public boolean deleteAllPostings(Profile profile)
    {
        List<Posting> postings = profile.getPostings();
        Boolean deleted = false;

        if(!postings.isEmpty())
        {
            postings.clear();
            deleted = true;
        }

        return deleted;
    }
    public Boolean deleteProfileById(Profile profile) {

        if (profile.getPostings().isEmpty()) {

            userMap.remove(profile.getUserName());
            profiles.remove(profile.getProfileId());

            return true;
        }
    
        return false;
    }

    public Boolean updatePosting(Profile profile, Posting updatePosting, Integer postingid) {

    
        List<Posting> postingList = profile.getPostings();
        for (Posting posting : postingList) {
            if(posting.getPostingId().equals(postingid))
            { 
                posting.setPostingText(updatePosting.getPostingText());
                postings.put(postingid, posting);
                return  true;
            }
        } 
         
        return false;
    } 

}
