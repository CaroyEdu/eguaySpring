package com.test.eguay.controller;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.service.AuctionService;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/PurchasedAuctions")
public class PurchasedAuctionController {
    public UserService getUserService() {
        return userService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected UserService userService;

    public AuctionService getAuctionService() {
        return auctionService;
    }
    @Autowired
    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    protected AuctionService auctionService;

    @GetMapping("")
    public String doShow(Model model , HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("user");

        model.addAttribute("purchasedAucs", this.userService.showPurchasedAuctions(user));
        model.addAttribute("user",user);
        return "purchasedAuctions";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model , HttpSession session , @PathVariable("id") Long id){
        UserDTO user = (UserDTO) session.getAttribute("user");
        AuctionDTO auctionDTO = this.auctionService.findById(id);
        this.userService.deletepurchasedAuction(user , auctionDTO);
        return "redirect/purchasedAuctions";
    }

}
