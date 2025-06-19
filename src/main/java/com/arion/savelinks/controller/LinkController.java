package com.arion.savelinks.controller;

import com.arion.savelinks.DTO.LinkDTO;
import com.arion.savelinks.DTO.UpdateLinkDTO;
import com.arion.savelinks.DTO.UpdateResponseDTO;
import com.arion.savelinks.Exception.UnauthorizedException;
import com.arion.savelinks.entity.Link;
import com.arion.savelinks.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LinkController {
    @Autowired
    private LinkService linkService;

    //configured with dto
    @GetMapping("/link")
    public ResponseEntity<List<LinkDTO>> getLinks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Link> allLinks = linkService.getLink(username);
        List<LinkDTO> allUserLinks = allLinks.stream().map(link -> new LinkDTO(
                link.getId(),
                link.getLink_title(),
                link.getLink(),
                link.getDescription()
        )).toList();
        return new ResponseEntity<>(allUserLinks, HttpStatus.OK);
    }

    //configured with dto
    @GetMapping("/link/{id}")
    public ResponseEntity<LinkDTO> getLinkById(@PathVariable String id) {

        Long linkId = Long.parseLong(id);
        Link linkById = linkService.getLinkById(linkId);
        LinkDTO singleLinkDTO = new LinkDTO(
                linkById.getId(),
                linkById.getLink(),
                linkById.getLink_title(),
                linkById.getDescription()
        );


        return new ResponseEntity<>(singleLinkDTO, HttpStatus.OK);


    }

    //configured with dto
    @PostMapping("/link")
    public ResponseEntity<?> addLink(@Valid @RequestBody LinkDTO link) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new UnauthorizedException("User not authenticated");
        }
        String username = authentication.getName();


        linkService.saveLink(link, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //already uses dto
    @PutMapping("/link/{id}")
    public ResponseEntity<?> updateLink(@PathVariable String id,@Valid @RequestBody UpdateLinkDTO link) {
        Long linkId = Long.parseLong(id);
        Link updatedLink = linkService.updateLink(linkId, link);
        UpdateResponseDTO updateResponseDTO=new UpdateResponseDTO(
                updatedLink.getId(),
                updatedLink.getLink_title(),

                updatedLink.getLink(),
                updatedLink.getDescription()
        );
        return new ResponseEntity<>(updateResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/link/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable String id) {
        Long linkId = Long.parseLong(id);
        linkService.deleteLink(linkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
