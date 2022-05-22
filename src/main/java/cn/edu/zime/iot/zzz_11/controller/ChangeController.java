package cn.edu.zime.iot.zzz_11.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangeController {

    @GetMapping("/toUsers")
    public String toUsers()
    {
        return "users";
    }

    @GetMapping("/toGetPosition")
    public String toTrack()
    {
        return "position";
    }

    @GetMapping("/toTrackBack")
    public String toTrackBack()
    {
        return "trackback";
    }

    @GetMapping("/toTrack")
    public String toGetPosition()
    {
        return "track";
    }

    @GetMapping("/toControl")
    public String toControl()
    {
        return "control";
    }
}
