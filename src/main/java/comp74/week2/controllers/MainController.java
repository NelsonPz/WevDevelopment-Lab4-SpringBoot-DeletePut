package comp74.week2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comp74.week2.model.Model;
import comp74.week2.model.Posting;
import comp74.week2.model.Profile;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class MainController {

    Model model;

    public MainController(Model model) {
        this.model = model;
    }

    @GetMapping("/profiles")
    public List<Profile> getProfileByUserName(
            @RequestParam(name = "userName", required = false, defaultValue = "") String userName) {
        System.out.println("Username: " + userName);
        if (userName.isEmpty())
            return model.getProfiles();
        else
            return model.getProfilesByUserName(userName);
    }

    @GetMapping("/profiles/{profileId}")
    public Profile getProfileById(@PathVariable Integer profileId) {
        return model.getProfileById(profileId);
    }

    @GetMapping("/profiles/{profileId}/postings")
    public List<Posting> getProfilePostings(@PathVariable Integer profileId) {
        List<Posting> postings = null;
        Profile profile = model.getProfileById(profileId);
        if (profile != null)
            postings = profile.getPostings();
        return postings;
    }

    @PostMapping("/profiles")
    public ResponseEntity<Profile> postMethodName(@RequestBody Profile profile) {
        Profile newProfile = new Profile(profile.getUserName());
        
        ResponseEntity<Profile> profileEntity = model.addProfile(newProfile);
       
        return profileEntity;
    }

    @PostMapping("/profiles/{profileId}/postings")
    public ResponseEntity<Posting> addPostingToProfile(@PathVariable Integer profileId, @RequestBody Posting posting) {
        Profile profile = model.getProfileById(profileId);
        if (profile != null) {
            ResponseEntity<Posting> newPosting = model.addPosting(profile, posting);
            return newPosting;
        } else {
            return null;
        }
    }

    @PostMapping("/profiles/postings")
    public ResponseEntity<Posting> addPostingByUsername(@RequestParam String userName, @RequestBody Posting posting) {
        Profile profile = model.getProfilesByUserName(userName).get(0);
        if (profile != null) {
            ResponseEntity<Posting> newPosting = model.addPosting(profile, posting);
            return newPosting;
        } else {
            return null;
        }
    }
    @DeleteMapping("/profiles/{profileId}/postings/{postingId}")
    public ResponseEntity<String> delelePostingById(@PathVariable Integer profileId, @PathVariable Integer postingId)
    {
        Profile profile = model.getProfileById(profileId);
       
        if(profile != null)
        {
            System.out.println("here1");
            boolean postdeleted = model.deletePosting(profile, postingId);
             
            if(postdeleted)
            {
                return new ResponseEntity<>("Success",HttpStatus.NO_CONTENT);
            } else
            {
                return new ResponseEntity<>("Either postingId or profileId does not exist.",HttpStatus.NOT_FOUND);
   
            }
        } else {
            return new ResponseEntity<>("Profile does not exist",HttpStatus.NOT_FOUND);
        }
        
    }
    @DeleteMapping("/profiles/{profileId}/postings")
    public ResponseEntity<String> deleteAllPostingByProfile(@PathVariable Integer profileId)
    {
        Profile profile = model.getProfileById(profileId);
        if(profile != null)
        {
            boolean postdeleted = model.deleteAllPostings(profile);
            if(postdeleted)
            {
                return new ResponseEntity<>("Success",HttpStatus.NO_CONTENT);
            } else
            {
                return new ResponseEntity<>("Either postingId or profileId does not exist.",HttpStatus.NOT_FOUND);
   
            }
        } else {
            return new ResponseEntity<>("Profile does not exist",HttpStatus.NOT_FOUND);
        }    
    }

    @DeleteMapping("/profiles/{profileId}")
    public ResponseEntity<String> deleleProfile(@PathVariable Integer profileId)
    {
        Profile profile = model.getProfileById(profileId);
       
        if(profile != null)
        {
            boolean postdeleted = model.deleteProfileById(profile);   
            if(postdeleted)
            {
                return new ResponseEntity<>("Success",HttpStatus.NO_CONTENT);
            } else
            {
                return new ResponseEntity<>("Profile exists but still has postings",HttpStatus.CONFLICT);       
            }
        } else {
            return new ResponseEntity<>("Profile does not exist",HttpStatus.NOT_FOUND);
        }
    } 

    @PutMapping("/profiles/{profileId}/postings/{postingId}")
    public ResponseEntity<String> putMethodName(@PathVariable Integer profileId, @PathVariable Integer postingId, @RequestBody Posting posting) {
        
        Profile profile = model.getProfileById(profileId);
       
        if(profile != null)
        {
            boolean postdeleted = model.updatePosting(profile,posting,postingId);   
            if(postdeleted)
            {
                return new ResponseEntity<>("Success",HttpStatus.NO_CONTENT);
            } else
            {
                return new ResponseEntity<>("Posting does not exist",HttpStatus.NOT_FOUND);       
            }
        } else {
            return new ResponseEntity<>("Profile does not exist",HttpStatus.NOT_FOUND);
        }
    }

}

    