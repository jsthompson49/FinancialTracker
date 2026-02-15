package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;

import java.util.List;

public class Schema {
    public static final String CATEGORY = "Category";

    private static final String ACCOUNT_TRANSFER = "AccountTransfer";
    private static final String CASH = "Cash";
    private static final String COLLEGE_AID = "CollegeAid";
    private static final String DONATION = "Donation";
    private static final String ENTERTAINMENT_EVENT = "EntertainmentEvent";
    private static final String HOME = "Home";
    private static final String INSURANCE = "Insurance";
    private static final String INTEREST = "Interest";
    private static final String SHOPPING = "Shopping";
    private static final String STREAMING_SERVICE = "StreamingService";
    private static final String TAXES = "Taxes";
    private static final String UTILITIES = "Utilities";
    private static final String WAGES = "Wages";

    public static final Tag CATEGORY_ACCOUNT_TRANSFER = new Tag(CATEGORY, ACCOUNT_TRANSFER);
    public static final Tag CATEGORY_CASH = new Tag(CATEGORY, CASH);
    public static final Tag CATEGORY_COLLEGE_AID = new Tag(CATEGORY, COLLEGE_AID);
    public static final Tag CATEGORY_DONATION = new Tag(CATEGORY, DONATION);
    public static final Tag CATEGORY_ENTERTAINMENT_EVENT = new Tag(CATEGORY, ENTERTAINMENT_EVENT);
    public static final Tag CATEGORY_HOME = new Tag(CATEGORY, HOME);
    public static final Tag CATEGORY_INSURANCE = new Tag(CATEGORY, INSURANCE);
    public static final Tag CATEGORY_INTEREST = new Tag(CATEGORY, INTEREST);
    public static final Tag CATEGORY_SHOPPING = new Tag(CATEGORY, SHOPPING);
    public static final Tag CATEGORY_STREAMING_SERVICE = new Tag(CATEGORY, STREAMING_SERVICE);
    public static final Tag CATEGORY_TAXES = new Tag(CATEGORY, TAXES);
    public static final Tag CATEGORY_UTILITIES = new Tag(CATEGORY, UTILITIES);
    public static final Tag CATEGORY_WAGES = new Tag(CATEGORY, WAGES);

    public static List<TagRule> TAG_RULES = List.of(
            new DescriptionPatternTagRule(".*(?i)(MN Pub Radio).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(UNBOUND DONATION).*", CATEGORY_DONATION),

            new DescriptionPatternTagRule(".*(?i)(ACESOLIDWASTE).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(CITY OF SHOREVIE UTILITIES).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(COMCAST).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(VERIZON WIRELESS PAYMENTS).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(XCELENERGY).*", CATEGORY_UTILITIES),

            new DescriptionPatternTagRule(".*(?i)(SAFE BOX ANNUAL FEE).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(EVERGREEN SHORES).*", CATEGORY_HOME),

            new DescriptionPatternTagRule(".*(?i)(ATM WITHDRAWAL).*", CATEGORY_CASH),

            new DescriptionPatternTagRule(".*(?i)(SeatGeek).*", CATEGORY_ENTERTAINMENT_EVENT),
            new DescriptionPatternTagRule(".*(?i)(NETFLIX).*", CATEGORY_STREAMING_SERVICE),

            new DescriptionPatternTagRule(".*(?i)(CAPITAL ONE).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(TARGET CARD SRVC).*", CATEGORY_SHOPPING),

            new DescriptionPatternTagRule(".*(?i)(STATE FARM).*", CATEGORY_INSURANCE),

            //new DescriptionPatternTagRule(".*(?i)(ONLINE TRANSFER TO THOMPSON S).*", CATEGORY_COLLEGE_AID),

            new DescriptionPatternTagRule(".*(?i)(IRS USATAXPYMT).*", CATEGORY_TAXES),
            new DescriptionPatternTagRule(".*(?i)(MN DEPT OF REVEN).*", CATEGORY_TAXES),

            new DescriptionPatternTagRule(".*(?i)(AUTOMATIC PAYMENT - THANK YOU).*", CATEGORY_ACCOUNT_TRANSFER),
            new DescriptionPatternTagRule(".*(?i)(WF Credit Card AUTO PAY).*", CATEGORY_ACCOUNT_TRANSFER),
            new DescriptionPatternTagRule(".*(?i)(INTEREST PAYMENT).*", CATEGORY_INTEREST),
            new DescriptionPatternTagRule(".*(?i)(MSPBNA BANK TRANSFER).*", CATEGORY_ACCOUNT_TRANSFER),
            new DescriptionPatternTagRule(".*(?i)(Amazon.com Servi PAYMENTS).*", CATEGORY_WAGES),
            new DescriptionPatternTagRule(".*(?i)(AMAZON.COM SVCS).*", CATEGORY_WAGES),
            new DescriptionPatternTagRule(".*(?i)(WELLS FARGO BANK PAYRLL DEP).*", CATEGORY_WAGES)
    );

    public static List<String> getSpendingCategoryValues() {
        return List.of(
                CASH,
                COLLEGE_AID,
                DONATION,
                ENTERTAINMENT_EVENT,
                HOME,
                INSURANCE,
                SHOPPING,
                STREAMING_SERVICE,
                TAXES,
                UTILITIES
        );
    }
}
