package com.test.eguay.repository;

import com.test.eguay.dto.UserDTO;
import com.test.eguay.entity.Auction;
import com.test.eguay.entity.Category;
import com.test.eguay.entity.FavoriteAuction;
import com.test.eguay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByusernameAndPassword(String username , String password);

    public User findUserByUserid(int id);

    @Query("select f.auctionByAuctionid from FavoriteAuction f where f.usersByUserid = :user")
    public List<Auction> findFavAuctions(@Param("user") User user );

    @Query("select f.auctionByAuctionid from PurchasedAuction f where f.usersByUserid = :user")
    public List<Auction> findPurchasedAuctions(@Param("user") User user );

    @Query("select f.categoryByCategoryid from UserCategory f where f.usersByUserid = :user")
    public List<Category> findFavCategories(@Param("user") User user );

    @Query("select f.auctionByAuctionid from FavoriteAuction f where f.usersByUserid = :user and lower( f.auctionByAuctionid.title ) like :name")
    public List<Auction> filterFavAuctions(@Param("user") User user , @Param("name") String filter );

    @Query("select f.auctionByAuctionid from PurchasedAuction f where f.usersByUserid = :user and lower( f.auctionByAuctionid.title ) like :name")
    public List<Auction> filterPurchasedAuctions(@Param("user") User user , @Param("name") String filter );

    public List<User> findAllByUseridIn(int[] ids);

    @Query("select u from User u join UserGroups ug on u.userid = ug.userid join Group g on g.groupid = ug.userid where g.groupid in :groupIds")
    List<User> findAllByUsersGroup_Group_IdIn(@Param("groupIds") long[] ids);
}
