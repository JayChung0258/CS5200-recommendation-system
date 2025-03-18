package user.tools;

import user.dal.*;
import user.model.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class Inserter {
    public static void main(String[] args) throws SQLException {
        // DAO instances
        UserIdentityDao userIdentityDao = UserIdentityDao.getInstance();
        UserProfileDao userProfileDao = UserProfileDao.getInstance();
        UserFinanceDao userFinanceDao = UserFinanceDao.getInstance();
        UserDeviceDao userDeviceDao = UserDeviceDao.getInstance();
        UserTechAwarenessDao userTechAwarenessDao = UserTechAwarenessDao.getInstance();
        UserSelfReportDao userSelfReportDao = UserSelfReportDao.getInstance();

        SocialMediaUsageDao socialMediaUsageDao = SocialMediaUsageDao.getInstance();
        EntertainmentUsageDao entertainmentUsageDao = EntertainmentUsageDao.getInstance();
        LifestyleUsageDao lifestyleUsageDao = LifestyleUsageDao.getInstance();
        
        // INSERT objects into the database
        UserIdentity user = new UserIdentity(1, 25, "Male", "USA");
        user = userIdentityDao.create(user);
        
        UserProfile profile = new UserProfile(1, "Software Engineer", "Single", true);
        profile = userProfileDao.create(profile);
        
        UserFinance finance = new UserFinance(1, new BigDecimal("5000.00"), new BigDecimal("2000.00"));
        finance = userFinanceDao.create(finance);
        
        UserDevice device = new UserDevice(1, "Smartphone", new BigDecimal("100.5"), "Phone", "5G Plan");
        device = userDeviceDao.create(device);
        
        UserTechAwareness techAwareness = new UserTechAwareness(1, 8, "High");
        techAwareness = userTechAwarenessDao.create(techAwareness);
        
        UserSelfReport selfReport = new UserSelfReport(1, 7, 5);
        selfReport = userSelfReportDao.create(selfReport);

        
        SocialMediaUsage socialMediaUsage = new SocialMediaUsage(1, new BigDecimal("2.5"), new BigDecimal("1.5"), null, 3, "Facebook", "Entertainment", 50, 5);
        socialMediaUsage = socialMediaUsageDao.create(socialMediaUsage);
        
        EntertainmentUsage entertainmentUsage = new EntertainmentUsage(1, new BigDecimal("3.0"), new BigDecimal("1.0"), new BigDecimal("0.5"), new BigDecimal("1.5"), null, 2, "Movies", "Netflix");
        entertainmentUsage = entertainmentUsageDao.create(entertainmentUsage);
        
        LifestyleUsage lifestyleUsage = new LifestyleUsage(1, new BigDecimal("6.5"), new BigDecimal("1.0"), new BigDecimal("0.5"), new BigDecimal("4.0"), new BigDecimal("2.0"), new BigDecimal("1.5"), new BigDecimal("0.8"), null, 10);
        lifestyleUsage = lifestyleUsageDao.create(lifestyleUsage);
        
        // READ
        UserIdentity retrievedUser = userIdentityDao.getUserById(1);
        System.out.format("Reading user: ID:%d Age:%d Gender:%s Country:%s\n",
            retrievedUser.getUserID(), retrievedUser.getAge(), retrievedUser.getGender(), retrievedUser.getCountry());
        
        List<UserProfile> profiles = userProfileDao.getAllUserProfiles();
        for (UserProfile p : profiles) {
            System.out.format("Looping profiles: ID:%d Occupation:%s Marital:%s Parental:%b\n",
                p.getProfileID(), p.getOccupation(), p.getMaritalStatus(), p.isParentalStatus());
        }
        
        List<UserFinance> finances = userFinanceDao.getAllUserFinances();
        for (UserFinance f : finances) {
            System.out.format("Looping finances: ID:%d Income:%.2f Expense:%.2f\n",
                f.getFinanceID(), f.getMonthlyIncomeUSD(), f.getMonthlyExpenditureUSD());
        }
        
        List<UserDevice> devices = userDeviceDao.getAllUserDevices();
        for (UserDevice d : devices) {
            System.out.format("Looping devices: ID:%d Type:%s Speed:%.2f Preferred:%s\n",
                d.getDeviceID(), d.getDeviceType(), d.getInternetSpeedMbps(), d.getPreferredDeviceForEntertainment());
        }
        
        List<SocialMediaUsage> socialUsages = socialMediaUsageDao.getAllSocialMediaUsageRecords();
        for (SocialMediaUsage s : socialUsages) {
            System.out.format("Looping social media: ID:%d DailyTime:%.2f Platform:%s\n",
                s.getUsageID(), s.getDailySocialMediaTime(), s.getPrimaryPlatform());
        }
        
        System.out.println("Data insertion and retrieval successful for all entities.");
    }
}
