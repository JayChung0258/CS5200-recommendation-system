package user.service;

import user.dal.*;
import user.model.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRecommendationService {
    private UserIdentityDao userIdentityDao;
    private UserProfileDao userProfileDao;
    private UserFinanceDao userFinanceDao;
    private UserDeviceDao userDeviceDao;
    private SocialMediaUsageDao socialMediaUsageDao;
    private EntertainmentUsageDao entertainmentUsageDao;
    private LifestyleUsageDao lifestyleUsageDao;

    public UserRecommendationService() {
        this.userIdentityDao = UserIdentityDao.getInstance();
        this.userProfileDao = UserProfileDao.getInstance();
        this.userFinanceDao = UserFinanceDao.getInstance();
        this.userDeviceDao = UserDeviceDao.getInstance();
        this.socialMediaUsageDao = SocialMediaUsageDao.getInstance();
        this.entertainmentUsageDao = EntertainmentUsageDao.getInstance();
        this.lifestyleUsageDao = LifestyleUsageDao.getInstance();
    }

    public List<String> recommendProducts(int userID) throws SQLException {
        List<String> recommendations = new ArrayList<>();
        
        // Fetch user data
        UserIdentity user = userIdentityDao.getUserById(userID);
        UserFinance finance = userFinanceDao.getUserFinanceById(userID);
        UserDevice device = userDeviceDao.getUserDeviceById(userID);
        SocialMediaUsage socialMedia = socialMediaUsageDao.getSocialMediaUsageById(userID);
        EntertainmentUsage entertainment = entertainmentUsageDao.getEntertainmentUsageById(userID);
        LifestyleUsage lifestyle = lifestyleUsageDao.getLifestyleUsageById(userID);

        // Rule-based recommendations
//        if (finance != null && finance.getMonthlyIncomeUSD().compareTo(new BigDecimal("5000")) > 0) {
//            recommendations.add("Luxury Travel Package");
//        }
//        if (socialMedia != null && socialMedia.getDailySocialMediaTime().compareTo(new BigDecimal("3.0")) > 0) {
//            recommendations.add("Social Media Content Subscription");
//        }
//        if (entertainment != null && "Netflix".equalsIgnoreCase(entertainment.getPreferredEntertainmentPlatform())) {
//            recommendations.add("Netflix Premium Subscription");
//        }
//        if (lifestyle != null && lifestyle.getAverageSleepTime().compareTo(new BigDecimal("5.0")) < 0) {
//            recommendations.add("Smart Sleep Tracker");
//        }
//        if (device != null && "Smartphone".equalsIgnoreCase(device.getDeviceType())) {
//            recommendations.add("Latest Smartphone Accessory");
//        }
        
        recommendations.add("Smart Sleep Tracker");

        return recommendations;
    }
}
