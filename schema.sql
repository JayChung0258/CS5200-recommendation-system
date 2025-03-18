DROP TABLE IF EXISTS social_media_usage;
DROP TABLE IF EXISTS entertainment_usage;
DROP TABLE IF EXISTS lifestyle_usage;
DROP TABLE IF EXISTS usage_base;
DROP TABLE IF EXISTS user_self_report;
DROP TABLE IF EXISTS user_tech_awareness;
DROP TABLE IF EXISTS user_device;
DROP TABLE IF EXISTS user_finance;
DROP TABLE IF EXISTS user_profile;
DROP TABLE IF EXISTS user_identity;

CREATE TABLE user_identity (
    userID INT AUTO_INCREMENT PRIMARY KEY,  
    age INT,
    gender VARCHAR(10),
    country VARCHAR(100)
);

CREATE TABLE user_profile (
    profileID INT AUTO_INCREMENT PRIMARY KEY,  
    userID INT UNIQUE,
    occupation VARCHAR(100),
    maritalStatus VARCHAR(50),
    parentalStatus TINYINT(1),
    FOREIGN KEY (userID) REFERENCES user_identity(userID) ON DELETE CASCADE
);

CREATE TABLE user_finance (
    financeID INT AUTO_INCREMENT PRIMARY KEY, 
    userID INT,
    monthlyIncomeUSD DECIMAL(10,2),
    monthlyExpenditureUSD DECIMAL(10,2),
    FOREIGN KEY (userID) REFERENCES user_identity(userID) ON DELETE CASCADE
);

CREATE TABLE user_device (
    deviceID INT AUTO_INCREMENT PRIMARY KEY,  
    userID INT,
    deviceType VARCHAR(50),
    internetSpeedMbps DECIMAL(5,2),
    preferredDeviceForEntertainment VARCHAR(50),
    dataPlanUsed VARCHAR(50),
    FOREIGN KEY (userID) REFERENCES user_identity(userID) ON DELETE CASCADE
);

CREATE TABLE user_tech_awareness (
    techID INT AUTO_INCREMENT PRIMARY KEY,  
    userID INT UNIQUE,
    techSavvinessLevel INT CHECK (techSavvinessLevel BETWEEN 1 AND 10),
    digitalWellBeingAwareness VARCHAR(50),
    FOREIGN KEY (userID) REFERENCES user_identity(userID) ON DELETE CASCADE
);

CREATE TABLE user_self_report (
    selfReportID INT AUTO_INCREMENT PRIMARY KEY,  
    userID INT,
    sleepQuality INT CHECK (sleepQuality BETWEEN 1 AND 10),
    socialIsolationFeeling INT CHECK (socialIsolationFeeling BETWEEN 1 AND 10),
    FOREIGN KEY (userID) REFERENCES user_identity(userID) ON DELETE CASCADE
);

CREATE TABLE usage_base (
    usageID INT AUTO_INCREMENT PRIMARY KEY,  
    userID INT,
    totalScreenTime DECIMAL(5,2),
    FOREIGN KEY (userID) REFERENCES user_identity(userID) ON DELETE CASCADE
);

CREATE TABLE social_media_usage (
    usageID INT PRIMARY KEY,  
    userID INT,
    dailySocialMediaTime DECIMAL(5,2),
    dailyMessagingTime DECIMAL(5,2),
    socialMediaPlatformsUsed INT,
    primaryPlatform VARCHAR(50),
    primarySocialMediaGoal VARCHAR(100),
    notificationsReceivedDaily INT,
    socialMediaFatigueLevel INT CHECK (socialMediaFatigueLevel BETWEEN 1 AND 10),
    FOREIGN KEY (usageID) REFERENCES usage_base(usageID) ON DELETE CASCADE
);

CREATE TABLE entertainment_usage (
    usageID INT PRIMARY KEY,  
    userID INT,
    dailyEntertainmentTime DECIMAL(5,2),
    dailyVideoContentTime DECIMAL(5,2),
    dailyGamingTime DECIMAL(5,2),
    dailyMusicListeningTime DECIMAL(5,2),
    subscriptionPlatforms INT,
    preferredContentType VARCHAR(100),
    preferredEntertainmentPlatform VARCHAR(100),
    FOREIGN KEY (usageID) REFERENCES usage_base(usageID) ON DELETE CASCADE
);

CREATE TABLE lifestyle_usage (
    usageID INT PRIMARY KEY,  
    userID INT,
    averageSleepTime DECIMAL(5,2),
    physicalActivityTime DECIMAL(5,2),
    readingTime DECIMAL(5,2),
    workStudyTime DECIMAL(5,2),
    timeSpentInOnlineCommunities DECIMAL(5,2),
    timeOnEducationalPlatforms DECIMAL(5,2),
    newsConsumptionTime DECIMAL(5,2),
    adInteractionCount INT,
    FOREIGN KEY (usageID) REFERENCES usage_base(usageID) ON DELETE CASCADE
);
