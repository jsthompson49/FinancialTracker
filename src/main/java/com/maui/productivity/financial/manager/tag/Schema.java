package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;

import java.util.List;

public class Schema {
    public static final String CATEGORY = "Category";

    public static final String ACCOUNT_TRANSFER = "AccountTransfer";
    public static final String CAR = "Car";
    public static final String CASH = "Cash";
    public static final String DONATION = "Donation";
    public static final String GIFT = "Gift";
    public static final String GROCERIES = "Groceries";
    public static final String HOBBIES = "Hobbies";
    public static final String HEALTH = "Health";
    public static final String HOME = "Home";
    public static final String INCOME_TAX = "IncomeTax";
    public static final String INSURANCE = "Insurance";
    public static final String INTEREST = "Interest";
    public static final String MEDIA = "Media";
    public static final String PETS = "Pets";
    public static final String PROPERTY_TAX = "PropertyTax";
    public static final String REAL_ESTATE = "RealEstate";
    public static final String REAL_ESTATE_COUNTY_ROAD_F = "RealEstateCountyRoadF";
    public static final String RESTAURANTS = "Restaurants";
    public static final String SERVICES = "Services";
    public static final String SHOPPING = "Shopping";
    public static final String SHOWS = "Shows";
    public static final String SPORTING_EVENT = "SportingEvent";
    public static final String TRAVEL = "Travel";
    public static final String TUITION = "Tuition";
    public static final String UTILITIES = "Utilities";
    public static final String WAGES = "Wages";

    public static final Tag CATEGORY_ACCOUNT_TRANSFER = new Tag(CATEGORY, ACCOUNT_TRANSFER);
    public static final Tag CATEGORY_CAR = new Tag(CATEGORY, CAR);
    public static final Tag CATEGORY_CASH = new Tag(CATEGORY, CASH);
    public static final Tag CATEGORY_DONATION = new Tag(CATEGORY, DONATION);
    public static final Tag CATEGORY_GIFT = new Tag(CATEGORY, GIFT);
    public static final Tag CATEGORY_GROCERIES = new Tag(CATEGORY, GROCERIES);
    public static final Tag CATEGORY_HOBBIES = new Tag(CATEGORY, HOBBIES);
    public static final Tag CATEGORY_HEALTH = new Tag(CATEGORY, HEALTH);
    public static final Tag CATEGORY_HOME = new Tag(CATEGORY, HOME);
    public static final Tag CATEGORY_INCOME_TAX = new Tag(CATEGORY, INCOME_TAX);
    public static final Tag CATEGORY_INSURANCE = new Tag(CATEGORY, INSURANCE);
    public static final Tag CATEGORY_INTEREST = new Tag(CATEGORY, INTEREST);
    public static final Tag CATEGORY_MEDIA = new Tag(CATEGORY, MEDIA);
    public static final Tag CATEGORY_PETS = new Tag(CATEGORY, PETS);
    public static final Tag CATEGORY_PROPERTY_TAX = new Tag(CATEGORY, PROPERTY_TAX);
    public static final Tag CATEGORY_REAL_ESTATE_COUNTY_ROAD_F = new Tag(CATEGORY, REAL_ESTATE_COUNTY_ROAD_F);
    public static final Tag CATEGORY_RESTAURANTS = new Tag(CATEGORY, RESTAURANTS);
    public static final Tag CATEGORY_SERVICES = new Tag(CATEGORY, SERVICES);
    public static final Tag CATEGORY_SHOPPING = new Tag(CATEGORY, SHOPPING);
    public static final Tag CATEGORY_SHOWS = new Tag(CATEGORY, SHOWS);
    public static final Tag CATEGORY_SPORTING_EVENT = new Tag(CATEGORY, SPORTING_EVENT);
    public static final Tag CATEGORY_TRAVEL = new Tag(CATEGORY, TRAVEL);
    public static final Tag CATEGORY_TUITION = new Tag(CATEGORY, TUITION);
    public static final Tag CATEGORY_UTILITIES = new Tag(CATEGORY, UTILITIES);
    public static final Tag CATEGORY_WAGES = new Tag(CATEGORY, WAGES);

    public static List<TagRule> TAG_RULES = List.of(
            new DescriptionPatternTagRule(".*(?i)(CONDO).*", CATEGORY_REAL_ESTATE_COUNTY_ROAD_F),

            new DescriptionPatternTagRule(".*(?i)(ATM WITHDRAWAL).*", CATEGORY_CASH),

            new DescriptionPatternTagRule(".*(?i)(GESMN).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(GoFundMe).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(METRO MEALS ON WHEELS).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(MN Pub Radio).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(UNBOUND ).*(?i)(DONATION).*", CATEGORY_DONATION),

            new DescriptionPatternTagRule(".*(?i)(BARNETT AUTO GROUP).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)( BP ).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(BP#).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(C RAMP).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(CENEX).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(CIRCLEK).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(CK HOLIDAY).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(HOLIDAY STATIONS).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(IMPARK).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(KIA AMERICA).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(KWIK TRIP).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(LINO LAKES QUICK STOP).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(STOP N GO).*", CATEGORY_CAR),
            new DescriptionPatternTagRule(".*(?i)(U OF M PARKING).*", CATEGORY_CAR),

            new DescriptionPatternTagRule(".*(?i)(ALDI).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(BERRY HILL FARM).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(CHARLIE'S COUNTY).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(COSTCO WHSE).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(CUB ).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(CUBGROCER).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(SP ATHLETIC BREWING).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(FESTIVAL FOODS).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(FRESH THYME).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(HELLOFRESH).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(JIM LIQUOR).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(LEGENDARY SPICE).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(STURDRINKS).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(KOWALSKI).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(MNFOOD.CLUB - TCFARM).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(Penzeys Spices).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(LETS DISH).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(SODASTREAM).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(TARE MARKET).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(TRADER JOE).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(WINESTREET SPIRITS).*", CATEGORY_GROCERIES),
            new DescriptionPatternTagRule(".*(?i)(WHO GIVES A CRAP).*", CATEGORY_GROCERIES),

            new DescriptionPatternTagRule(".*(?i)(AMBA Admin).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(EMERGENCY PHYSICIAN PR).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(ENDODONTIC ASSOCIATES).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(ENTIRA FAMILY).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(FAIRVIEW).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(HEALTHPARTNERS).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(METRO DENTALCARE).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(MIDWEST EAR NOSE).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(MN02-MOMENTA OMS).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(North Metro Dermatol).*", CATEGORY_HEALTH),
            new DescriptionPatternTagRule(".*(?i)(VISIONWORKS).*", CATEGORY_HEALTH),

            new DescriptionPatternTagRule(".*(?i)(Amazon web services).*", CATEGORY_HOBBIES),
            new DescriptionPatternTagRule(".*(?i)(MICHAELS STORES).*", CATEGORY_HOBBIES),

            new DescriptionPatternTagRule(".*(?i)(ACE HARDWARE).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(AT HOME STORE).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(BLINDSGALORE).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(SIMPLE AND GRAND).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(FLEET FARM).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(FRATTALLONES).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(EVERGREEN SHORES).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(PRECISION LANDSCAPE AND T).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(METRO HEATING & COOLING).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(METROHEATINGANDCOOLING).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(PLETSCHERS GREENHOUS).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(RING BASIC PLAN).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(ROCK GARDENS).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(SAFE BOX ANNUAL FEE).*", CATEGORY_HOME),
            new DescriptionPatternTagRule(".*(?i)(Wayfair).*", CATEGORY_HOME),

            new DescriptionPatternTagRule(".*(?i)(IRS ).*", CATEGORY_INCOME_TAX),
            new DescriptionPatternTagRule(".*(?i)(MN DEPT OF REVEN).*", CATEGORY_INCOME_TAX),

            new DescriptionPatternTagRule(".*(?i)(HOMESITE).*", CATEGORY_INSURANCE),
            new DescriptionPatternTagRule(".*(?i)(PROGRESSIVE INS).*", CATEGORY_INSURANCE),
            new DescriptionPatternTagRule(".*(?i)(STATE FARM).*", CATEGORY_INSURANCE),

            new DescriptionPatternTagRule(".*(?i)(ALLTRAILS.COM).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(Audible).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(APPLE.COM/BILL).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(BlueMountain).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(CONSUMER REPORTS).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(CSPI NUTRITION).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(NYTIMES).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(ONSTREET).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(SIRIUSXM).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(Spotify).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(DISNEY PLUS).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(NETFLIX).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(PARAMOUNT).*", CATEGORY_MEDIA),
            new DescriptionPatternTagRule(".*(?i)(PIONEER PRESS).*", CATEGORY_MEDIA),

            new DescriptionPatternTagRule(".*(?i)(ANIMAL PERFECT HEALTH).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(CHEWY).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(CHUCK&DONS).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(CORE PET ZONE).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(MINNESOTA VETERINARY).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(THEFARMERSDOG).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(PET EVOLUTION).*", CATEGORY_PETS),
            new DescriptionPatternTagRule(".*(?i)(Pet Supplies Plus).*", CATEGORY_PETS),

            new DescriptionPatternTagRule(".*(?i)(HOMETEAM INSPECTION).*", CATEGORY_REAL_ESTATE_COUNTY_ROAD_F),
            new DescriptionPatternTagRule(".*(?i)(RE-MAX Results).*", CATEGORY_REAL_ESTATE_COUNTY_ROAD_F),
            new DescriptionPatternTagRule(".*(?i)(TrustFunds - Nor Conv).*", CATEGORY_REAL_ESTATE_COUNTY_ROAD_F),

            new DescriptionPatternTagRule(".*(?i)(ACQUA).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(BALSAM LAKE BREWERY).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(BOLUDO).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(BOXCAR BAR).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(CAHILL BISTRO).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(CHIPOTLE).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(CHURCHILL ST).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(CRAVE FOOD & DRINK).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(CULVERS).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(DAIRY QUEEN).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(DOORDA).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(DICK & JOANS).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(DINO'S MEDITERRANEAN).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(EGGROLL QUEEN).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(FAT NATS EGGS).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(FORGOTTEN STAR BREW).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(GREATER TATER).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(HIGH PINES BREW).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(HODGEPOD).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(HOLA AREPA).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(JJ S POKE).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(KASSIDY'S STUDIO LOUNGE).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(KING COIL SPIRITS).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(KINGDOM BAKING).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(LA COCHINITA).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(LA COLONIA).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(LAKE MONSTER BREWING).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(NAMASTEIN).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(MI-SANT KITCHEN).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(ORCHID).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(OWAMNI).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(PANCHO).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(PAPA MURPHY).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(PASSPORT 06888002219BRAINERD).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(P.F.CHANG'S).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(PUNCH PIZZA).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(RAIL WERKS).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(REDGINGER).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(RUNNING ACES).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(SMASH PARK).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(ST PAUL BREWING).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(STANLEY'S).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(STARBUCKS).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(STATION NO 6).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(STUBBORN BROTHERS).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(SUISHIN).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(SUNSHINE SHOP).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(TASTE OF CHILE).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(TASTE OF SCANDINAVIA).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(TIMEANDPLACE).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(TRAVAIL).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(TRIA RESTAURANT).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(TOWN HALL BREWERY).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(VENN BREWING COMPANY).*", CATEGORY_RESTAURANTS),
            new DescriptionPatternTagRule(".*(?i)(DOORDASH WASHINGTO).*", CATEGORY_RESTAURANTS),

            new DescriptionPatternTagRule(".*(?i)(AARP).*", CATEGORY_SERVICES),
            new DescriptionPatternTagRule(".*(?i)(ARABELLA SALON).*", CATEGORY_SERVICES),
            new DescriptionPatternTagRule(".*(?i)(ERIKA ROSARIO).*", CATEGORY_SERVICES),
            new DescriptionPatternTagRule(".*(?i)(Experian).*", CATEGORY_SERVICES),
            new DescriptionPatternTagRule(".*(?i)(INVISIBLE FENCE).*", CATEGORY_SERVICES),
            new DescriptionPatternTagRule(".*(?i)(U OF M ALUMNI ASSOC).*", CATEGORY_SERVICES),

            new DescriptionPatternTagRule(".*(?i)(AMAZON MKTPL).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(AMAZON PRIME).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(AMAZON RETA).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(BARNES & NOBLE).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(BEST BUY).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(CAPITAL ONE).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(CVS/PHARMACY).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(DEMOCRACY CITY OF INDUSTRY).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(DICK'S SPORTING GOOD).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(DRY GOODS).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(DSW).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(WWW COSTCO COM).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(ETSY).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(FABFITFUN).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(FANATICS - MINN).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(FLOWER SHOP NETWORK).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(GOODTHINGS WB).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(Going Going Gone).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(HALF PRICE BOOKS).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(HUMMINGBIRD FLOR).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(KITSCH).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(MACYS).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(MICHAEL TOD).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(NIMO.INC).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(OLLIES FLOWERS).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(ROSE AND LOON).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(SAVERS).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(SCHULER SHOES).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(SWEET CHOCOLAT).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(TARGET CARD SRVC).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(THE STOCK MARKET).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(TRACTOR SUPPLY CO).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(U OF M - BOOKSTORES).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(UNCOMMONGOODS).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(VON MAUR).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(WALGREEN).*", CATEGORY_SHOPPING),
            new DescriptionPatternTagRule(".*(?i)(WILD USA).*", CATEGORY_SHOPPING),

            new DescriptionPatternTagRule(".*(?i)(ALLIANZ EVENT INS).*", CATEGORY_SHOWS),
            new DescriptionPatternTagRule(".*(?i)(EMAGINE).*", CATEGORY_SHOWS),
            new DescriptionPatternTagRule(".*(?i)(MARCUS OAKDALE CINE).*", CATEGORY_SHOWS),

            new DescriptionPatternTagRule(".*(?i)(ALLIANZ FIELD).*", CATEGORY_SPORTING_EVENT),
            new DescriptionPatternTagRule(".*(?i)(MINNESOTA UNITED FC).*", CATEGORY_SPORTING_EVENT),
            new DescriptionPatternTagRule(".*(?i)(SeatGeek).*", CATEGORY_SPORTING_EVENT),

            new DescriptionPatternTagRule(".*(?i)(AIRBNB).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(AGAVE BAR & GRILL).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(ANGRY TROUT CAFE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(AMEX RESY CREDIT).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(ARIA).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(BELLAGIO BOX OFFICE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(BLUEFIN BAY).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(BLUEFINHOTEL).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(CASCADE RESTAURANT).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(CHASERS 2).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(COCINA DEL BARRIO).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(COLECTIVO           Madison).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(COHO CAFE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(CORKTOWN DELI).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(DELTA AIR).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(JOYNES DEPT. STORE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(LA POLLERA).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(LAKE SUPERIOR TRADING).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(LYFT).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(MAC PARKING RESERVATIONS).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(MADISON CONCOURSE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(MSP HUDSON NEWS).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(HALF MOON EMPANADAS).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(KLOCKOW BREWING).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(PARK MGM).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(PARKMGM).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(PROST).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(BUFFALO EXCHANGE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(NYNY).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(ARTS DISTRICT CRAFT).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(705 OCEAN PRIME).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(ABLE BAKER BREWING).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(NACHO DADDY).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(CALIFORNIA PIZZA).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(CIELO).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(HUDSON ST).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(LAPOLLERA).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(MADISON WI).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(MIDDLETON WI).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(MONK'S MIDDLETON).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(ODYSSEYS UNLIMITED).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(OLIVAITAL).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(PARK/THE RESERVE).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(RAPIDSBREWINGCO).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(SAMBA BRAZILIAN).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(SAVANNAH).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(SCHROEDER BAKING).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(STATE LINE DISTILLER).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(STONE ARCH).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(THE GARDEN ASIAN).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(THUNDERLAND SHOWROOM).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(TYBEE ISLAND).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(UBER).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(URSA MINOR BREW).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(WATERSEDGE TRADING).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(WORLDS BEST DONUT).*", CATEGORY_TRAVEL),
            new DescriptionPatternTagRule(".*(?i)(WP BEVERAGES).*", CATEGORY_TRAVEL),

            //new DescriptionPatternTagRule(".*(?i)(ONLINE TRANSFER TO THOMPSON S).*", CATEGORY_COLLEGE_AID),

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
}
