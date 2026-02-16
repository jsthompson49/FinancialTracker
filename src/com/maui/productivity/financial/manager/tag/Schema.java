package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;

import java.util.List;

public class Schema {
    public static final String CATEGORY = "Category";

    private static final String ACCOUNT_TRANSFER = "AccountTransfer";
    private static final String CAR = "Car";
    private static final String CASH = "Cash";
    //private static final String COLLEGE_AID = "CollegeAid";
    private static final String DONATION = "Donation";
    private static final String ENTERTAINMENT_EVENT = "EntertainmentEvent";
    private static final String GROCERIES = "Groceries";
    private static final String HOBBIES = "Hobbies";
    private static final String HEALTH = "Health";
    private static final String HOME = "Home";
    private static final String INSURANCE = "Insurance";
    private static final String INTEREST = "Interest";
    private static final String MOVIES = "Movies";
    private static final String MEDIA = "Media";
    private static final String PETS = "Pets";
    //private static final String REIMBURSEMENT = "Reimbursement";
    private static final String RESTAURANTS = "Restaurants";
    private static final String SERVICES = "Services";
    private static final String SHOPPING = "Shopping";
    private static final String STREAMING_SERVICE = "StreamingService";
    private static final String TAXES = "Taxes";
    private static final String TRAVEL = "Travel";
    private static final String UTILITIES = "Utilities";
    private static final String WAGES = "Wages";

    public static final Tag CATEGORY_ACCOUNT_TRANSFER = new Tag(CATEGORY, ACCOUNT_TRANSFER);
    public static final Tag CATEGORY_CAR = new Tag(CATEGORY, CAR);
    public static final Tag CATEGORY_CASH = new Tag(CATEGORY, CASH);
    //public static final Tag CATEGORY_COLLEGE_AID = new Tag(CATEGORY, COLLEGE_AID);
    public static final Tag CATEGORY_DONATION = new Tag(CATEGORY, DONATION);
    public static final Tag CATEGORY_ENTERTAINMENT_EVENT = new Tag(CATEGORY, ENTERTAINMENT_EVENT);
    public static final Tag CATEGORY_GROCERIES = new Tag(CATEGORY, GROCERIES);
    public static final Tag CATEGORY_HOBBIES = new Tag(CATEGORY, HOBBIES);
    public static final Tag CATEGORY_HEALTH = new Tag(CATEGORY, HEALTH);
    public static final Tag CATEGORY_HOME = new Tag(CATEGORY, HOME);
    public static final Tag CATEGORY_INSURANCE = new Tag(CATEGORY, INSURANCE);
    public static final Tag CATEGORY_INTEREST = new Tag(CATEGORY, INTEREST);
    public static final Tag CATEGORY_MOVIES = new Tag(CATEGORY, MOVIES);
    public static final Tag CATEGORY_MEDIA = new Tag(CATEGORY, MEDIA);
    public static final Tag CATEGORY_PETS = new Tag(CATEGORY, PETS);
    //public static final Tag CATEGORY_REIMBURSEMENT = new Tag(CATEGORY, REIMBURSEMENT);
    public static final Tag CATEGORY_RESTAURANTS = new Tag(CATEGORY, RESTAURANTS);
    public static final Tag CATEGORY_SERVICES = new Tag(CATEGORY, SERVICES);
    public static final Tag CATEGORY_SHOPPING = new Tag(CATEGORY, SHOPPING);
    public static final Tag CATEGORY_STREAMING_SERVICE = new Tag(CATEGORY, STREAMING_SERVICE);
    public static final Tag CATEGORY_TAXES = new Tag(CATEGORY, TAXES);
    public static final Tag CATEGORY_TRAVEL = new Tag(CATEGORY, TRAVEL);
    public static final Tag CATEGORY_UTILITIES = new Tag(CATEGORY, UTILITIES);
    public static final Tag CATEGORY_WAGES = new Tag(CATEGORY, WAGES);

    public static List<TagRule> TAG_RULES = List.of(
            new DescriptionPatternTagRule(".*(?i)(ATM WITHDRAWAL).*", CATEGORY_CASH),

            new DescriptionPatternTagRule(".*(?i)(GESMN).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(GoFundMe).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(METRO MEALS ON WHEELS).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(MN Pub Radio).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(UNBOUND DONATION).*", CATEGORY_DONATION),

            new DescriptionPatternTagRule(".*(?i)(C RAMP).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(CK HOLIDAY).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(HOLIDAY STATIONS).*", CATEGORY_CAR),

            new DescriptionPatternTagRule(".*(?i)(MINNESOTA UNITED FC).*", CATEGORY_ENTERTAINMENT_EVENT),
            new DescriptionPatternTagRule(".*(?i)(SeatGeek).*", CATEGORY_ENTERTAINMENT_EVENT),

            new DescriptionPatternTagRule(".*(?i)(DISNEY PLUS).*", CATEGORY_STREAMING_SERVICE),
            new DescriptionPatternTagRule(".*(?i)(NETFLIX).*", CATEGORY_STREAMING_SERVICE),
            new DescriptionPatternTagRule(".*(?i)(PARAMOUNT).*", CATEGORY_STREAMING_SERVICE),

            new DescriptionPatternTagRule(".*(?i)(COSTCO WHSE).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(CUB ).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(CUBGROCER).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(SP ATHLETIC BREWING).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(HELLOFRESH).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(LEGENDARY SPICE).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(STURDRINKS).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(KOWALSKI).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(Penzeys Spices).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(LETS DISH).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(TRADER JOE).*", CATEGORY_GROCERIES),

            new DescriptionPatternTagRule(".*(?i)(ENTIRA FAMILY CLINICS).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(FAIRVIEW HLTH).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(HEALTHPARTNERS).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(North Metro Dermatolog).*", CATEGORY_HEALTH),

            new DescriptionPatternTagRule(".*(?i)(Amazon web services).*", CATEGORY_HOBBIES),
            new DescriptionPatternTagRule(".*(?i)(MICHAELS STORES).*", CATEGORY_HOBBIES),

            new DescriptionPatternTagRule(".*(?i)(BLINDSGALORE).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(SIMPLE AND GRAND).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(FRATTALLONES).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(EVERGREEN SHORES).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(PRECISION LANDSCAPE AND T).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(METRO HEATING & COOLING).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(RING BASIC PLAN).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(SAFE BOX ANNUAL FEE).*", CATEGORY_HOME),

            new DescriptionPatternTagRule(".*(?i)(STATE FARM).*", CATEGORY_INSURANCE),

            new DescriptionPatternTagRule(".*(?i)(Audible).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(APPLE.COM/BILL).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(NYTIMES).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(ONSTREET).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(SIRIUSXM).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(Spotify).*", CATEGORY_MEDIA),

            new DescriptionPatternTagRule(".*(?i)(EMAGINE WHITE BEAR).*", CATEGORY_MOVIES),
            new DescriptionPatternTagRule(".*(?i)(MARCUS OAKDALE CINE).*", CATEGORY_MOVIES),

            new DescriptionPatternTagRule(".*(?i)(ANIMAL PERFECT HEALTH).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(CORE PET ZONE).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(MINNESOTA VETERINARY).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(THEFARMERSDOG).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(PET EVOLUTION).*", CATEGORY_PETS),

            new DescriptionPatternTagRule(".*(?i)(BOLUDO).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(EGGROLL QUEEN).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(PANCHO).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(PAPA MURPHY).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(P.F.CHANG'S).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(TRIA RESTAURANT).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(LAKE MONSTER BREWING).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(TOWN HALL BREWERY).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(SUISHIN).*", CATEGORY_RESTAURANTS),

            new DescriptionPatternTagRule(".*(?i)(AARP).*", CATEGORY_SERVICES),
            new DescriptionPatternTagRule(".*(?i)(ERIKA ROSARIO).*", CATEGORY_SERVICES),
            new DescriptionPatternTagRule(".*(?i)(U OF M ALUMNI ASSOC).*", CATEGORY_SERVICES),

            new DescriptionPatternTagRule(".*(?i)(AMAZON MKTPL).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(AMAZON PRIME).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(AMAZON RETA).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(CAPITAL ONE).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(CVS/PHARMACY).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(WWW COSTCO COM).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(GOODTHINGS WB).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(HALF PRICE BOOKS).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(TARGET CARD SRVC).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(TRACTOR SUPPLY CO).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(WALGREENS).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(FLOWER SHOP NETWORK).*", CATEGORY_SHOPPING),

            new DescriptionPatternTagRule(".*(?i)(BELLAGIO BOX OFFICE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(ARIA).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(COCINA DEL BARRIO).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(LYFT).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(MAC PARKING RESERVATIONS).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(MSP HUDSON NEWS).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(HALF MOON EMPANADAS).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(PARK MGM).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(PARKMGM).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(BUFFALO EXCHANGE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(NYNY).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(ARTS DISTRICT CRAFT).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(705 OCEAN PRIME).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(ABLE BAKER BREWING).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(NACHO DADDY).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(CALIFORNIA PIZZA).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(HUDSON ST).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(PARK/THE RESERVE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(UBER).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(THUNDERLAND SHOWROOM).*", CATEGORY_TRAVEL),

            //new DescriptionPatternTagRule(".*(?i)(ONLINE TRANSFER TO THOMPSON S).*", CATEGORY_COLLEGE_AID),

            new DescriptionPatternTagRule(".*(?i)(IRS USATAXPYMT).*", CATEGORY_TAXES),
            new DescriptionPatternTagRule(".*(?i)(MN DEPT OF REVEN).*", CATEGORY_TAXES),

            new DescriptionPatternTagRule(".*(?i)(ACESOLIDWASTE).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(CITY OF SHOREVIE UTILITIES).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(COMCAST).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(VERIZON WIRELESS PAYMENTS).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(XCELENERGY).*", CATEGORY_UTILITIES),

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
                CAR,
                CASH,
                //COLLEGE_AID,
                DONATION,
                ENTERTAINMENT_EVENT,
                GROCERIES,
                HEALTH,
                HOBBIES,
                HOME,
                INSURANCE,
                MEDIA,
                MOVIES,
                PETS,
                //REIMBURSEMENT,
                RESTAURANTS,
                SERVICES,
                SHOPPING,
                STREAMING_SERVICE,
                TAXES,
                TRAVEL,
                UTILITIES
        );
    }
}
