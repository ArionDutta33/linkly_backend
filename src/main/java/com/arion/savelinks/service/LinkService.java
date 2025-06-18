package com.arion.savelinks.service;

import com.arion.savelinks.DTO.LinkDTO;
import com.arion.savelinks.DTO.UpdateLinkDTO;
import com.arion.savelinks.Exception.LinkNotFoundException;
import com.arion.savelinks.entity.Link;
import com.arion.savelinks.entity.User;
import com.arion.savelinks.repository.LinkRepository;
import com.arion.savelinks.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LinkService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private LinkRepository linkRepository;

    public List<Link> getLink(String username) {
        Optional<User> byUsername = userDetailsRepository.findByUsername(username);
        if(!byUsername.isPresent()){
            throw new RuntimeException("User not found with username"+username);
        }
        User existingUser=byUsername.get();
        return existingUser.getLinks();


    }

    public void saveLink(LinkDTO dto, String username) {
        Optional<User> user = userDetailsRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found with the username" + username);
        }
        User existingUser = user.get();
        Link link=new Link();
        link.setUser(existingUser);
        link.setLink_title(dto.getLink_title());
        link.setLink(dto.getLink());
        link.setDescription(dto.getDescription());
        linkRepository.save(link);
    }

    public Link getLinkById(Long id) {
        return linkRepository.findById(id).orElseThrow(() -> new LinkNotFoundException("No link found"));
    }


    public void updateLink(Long id, UpdateLinkDTO linkDTO) {
        Optional<Link> linkById = linkRepository.findById(id);
        if (linkById.isPresent()) {
            Link existingLink = linkById.get();

            if (linkDTO.getLink() != null && !linkDTO.getLink().trim().isEmpty() && !linkDTO.getLink().equals(existingLink.getLink())) {
                existingLink.setLink(linkDTO.getLink());
            }

            if (linkDTO.getLink_title() != null && !linkDTO.getLink_title().trim().isEmpty() && !linkDTO.getLink_title().equals(existingLink.getLink_title())) {
                existingLink.setLink_title(linkDTO.getLink_title());
            }

            if (linkDTO.getDescription() != null && !linkDTO.getDescription().trim().isEmpty() && !linkDTO.getDescription().equals(existingLink.getDescription())) {
                existingLink.setDescription(linkDTO.getDescription());
            }

            linkRepository.save(existingLink);
        } else {
            throw new LinkNotFoundException("Link not found");
        }
    }

    public void deleteLink(Long linkId) {
        linkRepository.deleteById(linkId);

    }

}
