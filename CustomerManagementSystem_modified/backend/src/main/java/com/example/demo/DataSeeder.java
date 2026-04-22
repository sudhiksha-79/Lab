package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * DataSeeder — runs once on startup and populates ALL tables with realistic bulk data.
 * Covers every field including all audit fields (crudValue, hostTimestamp, localTimestamp,
 * acceptanceTimestamp, acceptanceTimestampUTCoffset, uuid, userID, workstationID, programID).
 *
 * Generates 50 customers, each with:
 *   - 1 CustomerDetails record
 *   - 2–4 CustomerName component rows (FIRST, LAST, MIDDLE, PREFIX/SUFFIX)
 *   - 2–4 CustomerAddress component rows (STREET, CITY, STATE, PINCODE, COUNTRY)
 *   - 1 CustomerContactDetails row
 *   - 1–2 CustomerIdentification rows
 *   - 1 CustomerClassification row
 *   - 1 CustomerProofOfIdentity row
 *
 * crudValue distribution: ~70% C (active), ~20% U (updated), ~10% D (soft-deleted).
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final CustomerDetailsRepository detailsRepo;
    private final CustomerNameRepository nameRepo;
    private final CustomerAddressRepository addressRepo;
    private final CustomerContactDetailsRepository contactRepo;
    private final CustomerIdentificationRepository identificationRepo;
    private final CustomerClassificationRepository classificationRepo;
    private final CustomerProofOfIdentityRepository proofRepo;

    public DataSeeder(CustomerDetailsRepository detailsRepo,
                      CustomerNameRepository nameRepo,
                      CustomerAddressRepository addressRepo,
                      CustomerContactDetailsRepository contactRepo,
                      CustomerIdentificationRepository identificationRepo,
                      CustomerClassificationRepository classificationRepo,
                      CustomerProofOfIdentityRepository proofRepo) {
        this.detailsRepo = detailsRepo;
        this.nameRepo = nameRepo;
        this.addressRepo = addressRepo;
        this.contactRepo = contactRepo;
        this.identificationRepo = identificationRepo;
        this.classificationRepo = classificationRepo;
        this.proofRepo = proofRepo;
    }

    // ─────────────────────────────── DATA POOLS ────────────────────────────────

    // Uncommon + common first names (mix of Indian, international, rare)
    private static final String[] FIRST_NAMES = {
        "Aarav", "Vihaan", "Reyansh", "Advik", "Sai", "Dhruv", "Arjun", "Rohan",
        "Priya", "Ananya", "Kavya", "Diya", "Ishaan", "Vivaan", "Arnav", "Kabir",
        "Zephyr", "Idris", "Lilavati", "Narayani", "Balthazar", "Xiomara", "Olusegun",
        "Thaddeus", "Seraphina", "Evangeline", "Caoimhe", "Siobhan", "Aisling", "Fionnuala",
        "Ptolemy", "Lysander", "Calliope", "Elowen", "Saoirse", "Eudoxia", "Zuberi",
        "Kiriakos", "Radovan", "Miroslava", "Anatoliy", "Desdemona", "Apollinaris",
        "Mehetabel", "Crisanta", "Valentijn", "Bartholomeus", "Wenceslaus", "Hildegard",
        "Takahiro", "Yoshitaka", "Kumiko", "Haruki", "Ayesha", "Farrukh", "Dilnoza"
    };

    private static final String[] LAST_NAMES = {
        "Sharma", "Patel", "Iyer", "Nair", "Reddy", "Krishnamurthy", "Venkataraman",
        "Balasubramaniam", "Chakraborty", "Bhattacharyya", "Mukherjee", "Bandyopadhyay",
        "Raghunathan", "Subramaniam", "Parthasarathy", "Anantharaman", "Viswanadhan",
        "Winterbottom", "Featherstone", "Ramachandran", "Quisenberry", "Throckmorton",
        "Vandenberghe", "Szczepanski", "Wojciechowski", "Papadimitriou", "Hatzigeorgiou",
        "Abramowitz", "Schwarzenberger", "Narasimhaswamy", "Govindarajan", "Lakshminarayan",
        "Okonkwo", "Nwachukwu", "Abiodun", "Beauchamp", "Cholmondeley", "Featherstonehaugh",
        "Sidebottom", "Shufflebotham", "Higginbotham", "Ravenscroft", "Thistlethwaite",
        "Yamamoto", "Watanabe", "Nakagawa", "Srivastava", "Kulkarni", "Bhosale", "Deshmukh"
    };

    private static final String[] MIDDLE_NAMES = {
        "Kumar", "Devi", "Prasad", "Bai", "Rao", "Lal", "Kumari", "Ram",
        "James", "Marie", "Anne", "John", "Grace", "Rose", "Elizabeth",
        "Venkata", "Srinivasa", "Narasimha", "Raghavendra", "Basavanna",
        "Xavier", "Ignatius", "Bartholomew", "Celestine", "Scholastica"
    };

    private static final String[] PREFIXES   = {"Mr.", "Mrs.", "Ms.", "Dr.", "Prof.", "Er.", "Adv.", "Capt.", "Col."};
    private static final String[] SUFFIXES   = {"Jr.", "Sr.", "II", "III", "Ph.D.", "M.D.", "Esq.", "IAS", "IPS"};

    private static final String[] STREETS = {
        "12, Gandhi Nagar Main Road", "45/B, Ambedkar Street", "7, MG Road",
        "Plot 23, Sector 14, Dwarka", "8th Cross, Malleswaram", "No.3, Pantheon Road",
        "17A, Park Lane", "56, Residency Road", "Flat 4B, Sunshine Apartments",
        "C-42, Defence Colony", "Bungalow 9, Marine Lines", "77, Jawahar Nagar",
        "2/A, Elgin Road", "103, Anna Salai", "Kothi 5, Model Town",
        "H.No.234, Sector 22-B", "Old No.12, New No.24, Habibullah Road",
        "Domlur Layout, 3rd Stage", "14, Cantonment Road", "9, Sarojini Devi Road"
    };

    private static final String[] CITIES = {
        "Mumbai", "Delhi", "Bengaluru", "Chennai", "Hyderabad", "Kolkata", "Pune",
        "Ahmedabad", "Jaipur", "Surat", "Lucknow", "Kanpur", "Nagpur", "Indore",
        "Thane", "Bhopal", "Visakhapatnam", "Vadodara", "Coimbatore", "Agra"
    };

    private static final String[] STATES = {
        "Maharashtra", "Karnataka", "Tamil Nadu", "Telangana", "Uttar Pradesh",
        "West Bengal", "Rajasthan", "Gujarat", "Madhya Pradesh", "Andhra Pradesh",
        "Kerala", "Punjab", "Haryana", "Bihar", "Odisha"
    };

    private static final String[] COUNTRIES = {
        "India", "India", "India", "India", "India",   // India dominant
        "United States", "United Kingdom", "Canada",
        "Australia", "Germany", "Singapore", "UAE"
    };

    private static final String[] PINCODES = {
        "400001", "110001", "560001", "600001", "500001", "700001", "411001",
        "380001", "302001", "395001", "226001", "208001", "440001", "452001",
        "400601", "462001", "530001", "390001", "641001", "282001"
    };

    private static final String[] CUSTOMER_TYPES = {
        "INDIVIDUAL", "CORPORATE", "NRI", "MINOR", "TRUST", "PARTNERSHIP",
        "SOLE_PROPRIETOR", "HUF", "GOVERNMENT", "NGO"
    };

    private static final String[] GENDERS      = {"MALE", "FEMALE", "TRANSGENDER", "NON_BINARY", "PREFER_NOT_TO_SAY"};
    private static final String[] STATUSES     = {"ACTIVE", "INACTIVE", "DORMANT", "SUSPENDED", "DECEASED", "KYC_PENDING"};
    private static final String[] LANGUAGES    = {"ENGLISH", "HINDI", "KANNADA", "TAMIL", "TELUGU", "MALAYALAM", "MARATHI", "BENGALI", "GUJARATI", "PUNJABI", "ODIA", "URDU"};
    private static final String[] ADDR_TYPES   = {"STREET_NUMBER", "STREET_NAME", "CITY", "STATE", "PINCODE", "COUNTRY", "LANDMARK", "PO_BOX"};
    private static final String[] ID_TYPES     = {"AADHAAR", "PAN", "PASSPORT", "VOTER_ID", "DRIVING_LICENSE", "GSTIN", "TAN", "EMPLOYEE_ID"};
    private static final String[] CLASS_TYPES  = {"PREMIUM", "STANDARD", "VIP", "PLATINUM", "GOLD", "SILVER", "BRONZE", "PREFERRED", "INSTITUTIONAL"};
    private static final String[] CLASS_VALUES = {"TIER_1", "TIER_2", "TIER_3", "HIGH_NETWORTH", "MASS_AFFLUENT", "RETAIL", "CORPORATE", "SME"};
    private static final String[] PROOF_TYPES  = {"AADHAAR_CARD", "PAN_CARD", "PASSPORT", "VOTER_ID_CARD", "RATION_CARD", "UTILITY_BILL", "BANK_STATEMENT", "SALARY_SLIP", "ITR", "GST_CERT"};
    private static final String[] PROGRAMS     = {"CRM_SVC", "ONBOARD_SVC", "KYC_SVC", "ADDR_SVC", "AUDIT_SVC", "BULK_LOAD", "MIGRATION_SVC", "PORTAL_UI", "MOBILE_APP", "AGENT_SVC"};
    private static final String[] WORKSTATIONS = {"WS-MUM-01", "WS-DEL-02", "WS-BLR-03", "WS-CHN-04", "WS-HYD-05", "WS-KOL-06", "WS-PUN-07", "WS-AHM-08", "WS-SRV-09", "WS-APP-10"};
    private static final String[] USER_IDS     = {"USR001", "USR002", "USR003", "USR004", "USR005", "USR006", "USR007", "USR008", "ADMIN", "SYSTEM", "BATCH_USR", "SEEDER"};

    // ─────────────────────────────── HELPERS ───────────────────────────────────

    private final Random rand = new Random(42); // fixed seed → reproducible

    private <T> T pick(T[] arr)            { return arr[rand.nextInt(arr.length)]; }
    private <T> T pick(List<T> list)       { return list.get(rand.nextInt(list.size())); }

    private String uuid()                  { return UUID.randomUUID().toString(); }

    /** Random timestamp between Jan 2020 and today */
    private Timestamp randomTs() {
        long start = LocalDateTime.of(2020, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
        long end   = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        return Timestamp.from(java.time.Instant.ofEpochSecond(start + (long)(rand.nextDouble() * (end - start))));
    }

    /** Random SQL date between two years */
    private Date randomDate(int fromYear, int toYear) {
        LocalDate from = LocalDate.of(fromYear, 1, 1);
        LocalDate to   = LocalDate.of(toYear, 12, 31);
        long days = to.toEpochDay() - from.toEpochDay();
        return Date.valueOf(from.plusDays(rand.nextInt((int) days)));
    }

    /**
     * UTC offset timestamp — simulates acceptance time slightly after host time,
     * with a realistic timezone offset baked in as a timestamp value.
     */
    private Timestamp utcOffsetTs(Timestamp base) {
        // IST offset = UTC+5:30 = 19800 seconds
        return new Timestamp(base.getTime() + 19800_000L);
    }

    /** Pick a crud value: 70% C, 20% U, 10% D */
    private char pickCrud() {
        int n = rand.nextInt(10);
        if (n < 7) return 'C';
        if (n < 9) return 'U';
        return 'D';
    }

    /** Generate a realistic Aadhaar-style 12-digit number */
    private String aadhaar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) sb.append(rand.nextInt(10));
        return sb.toString();
    }

    /** Generate a PAN card number: ABCDE1234F style */
    private String pan() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return "" + letters.charAt(rand.nextInt(26))
                 + letters.charAt(rand.nextInt(26))
                 + letters.charAt(rand.nextInt(26))
                 + letters.charAt(rand.nextInt(26))
                 + letters.charAt(rand.nextInt(26))
                 + (rand.nextInt(9) + 1)
                 + rand.nextInt(10)
                 + rand.nextInt(10)
                 + rand.nextInt(10)
                 + letters.charAt(rand.nextInt(26));
    }

    /** Generate a passport number */
    private String passport() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return "" + letters.charAt(rand.nextInt(26))
                 + (rand.nextInt(9000000) + 1000000);
    }

    /** 10-digit mobile number */
    private String phone() {
        long n = 7000000000L + (long)(rand.nextDouble() * 2999999999L);
        return String.valueOf(n);
    }

    /** Email */
    private String email(String first, String last) {
        String[] domains = {"gmail.com", "yahoo.co.in", "hotmail.com", "outlook.com", "rediffmail.com", "icloud.com"};
        return (first.toLowerCase() + "." + last.toLowerCase() + rand.nextInt(999) + "@" + pick(domains))
               .replaceAll("[^a-z0-9.@]", "");
    }

    // ───────────────────────────── MAIN SEEDER ─────────────────────────────────

    @Override
    public void run(String... args) throws Exception {

        // Skip if data already exists
        if (detailsRepo.count() > 0) {
            System.out.println("[DataSeeder] Data already present — skipping seed.");
            return;
        }

        System.out.println("[DataSeeder] Starting bulk data generation...");

        int totalCustomers = 50;

        for (int i = 0; i < totalCustomers; i++) {
            String firstName  = pick(FIRST_NAMES);
            String lastName   = pick(LAST_NAMES);
            String middleName = pick(MIDDLE_NAMES);

            // ── 1. CustomerDetails ──────────────────────────────────────────────
            CustomerDetails cd = new CustomerDetails();
            Timestamp hostTs   = randomTs();
            Timestamp localTs  = new Timestamp(hostTs.getTime() + rand.nextInt(3600) * 1000L);
            Timestamp acptTs   = new Timestamp(localTs.getTime() + rand.nextInt(600) * 1000L);

            cd.setCustomerType(pick(CUSTOMER_TYPES));
            cd.setCustomerDOB(randomDate(1940, 2005));
            cd.setCustomerStatus(pick(STATUSES));
            cd.setCustomerGender(pick(GENDERS));
            cd.setCustomerPreferredLanguage(pick(LANGUAGES));
            cd.setCustomerCountryOrigin(pick(COUNTRIES));
            cd.setEffectiveDate(randomDate(2018, 2024));
            cd.setCrudValue(pickCrud());
            cd.setUserID(pick(USER_IDS));
            cd.setWorkstationID(pick(WORKSTATIONS));
            cd.setProgramID(pick(PROGRAMS));
            cd.setAcceptanceTimestamp(acptTs);
            cd.setAcceptanceTimestampUTCoffset(utcOffsetTs(acptTs));
            cd.setUuid(uuid());
            // hostTimestamp & localTimestamp are auto-filled by @CreatedDate / @LastModifiedDate
            CustomerDetails savedCd = detailsRepo.save(cd);
            Long custId = savedCd.getId(); // FK for child tables

            // ── 2. CustomerName — FIRST, LAST, always; MIDDLE + PREFIX/SUFFIX random ──
            List<String[]> nameParts = new ArrayList<>();
            nameParts.add(new String[]{"FIRST_NAME",  firstName});
            nameParts.add(new String[]{"LAST_NAME",   lastName});
            if (rand.nextBoolean()) nameParts.add(new String[]{"MIDDLE_NAME", middleName});
            if (rand.nextInt(4) == 0) nameParts.add(new String[]{"PREFIX", pick(PREFIXES)});
            if (rand.nextInt(5) == 0) nameParts.add(new String[]{"SUFFIX", pick(SUFFIXES)});

            for (String[] part : nameParts) {
                CustomerName cn = new CustomerName();
                Timestamp nHost = randomTs();
                Timestamp nAcpt = new Timestamp(nHost.getTime() + rand.nextInt(300) * 1000L);
                cn.setCustomerIdentifier(custId);
                cn.setCustomerNameComponentType(part[0]);
                cn.setCustomerNameComponentValue(part[1]);
                cn.setEffectiveDate(randomDate(2018, 2024));
                cn.setCrudValue(pickCrud());
                cn.setUserID(pick(USER_IDS));
                cn.setWorkstationID(pick(WORKSTATIONS));
                cn.setProgramID(pick(PROGRAMS));
                cn.setAcceptanceTimestamp(nAcpt);
                cn.setAcceptanceTimestampUTCoffset(utcOffsetTs(nAcpt));
                cn.setUuid(uuid());
                nameRepo.save(cn);
            }

            // ── 3. CustomerAddress — STREET, CITY, STATE, PINCODE, COUNTRY ──────
            String city    = pick(CITIES);
            String state   = pick(STATES);
            String country = pick(COUNTRIES);
            String pincode = pick(PINCODES);
            String street  = pick(STREETS);
            String landmark = "Near " + pick(new String[]{"Railway Station", "Bus Stand", "Post Office", "Temple", "Church", "Mosque", "School", "Hospital", "Park", "Market"});

            List<String[]> addrParts = new ArrayList<>();
            addrParts.add(new String[]{"STREET",   street});
            addrParts.add(new String[]{"CITY",     city});
            addrParts.add(new String[]{"STATE",    state});
            addrParts.add(new String[]{"PINCODE",  pincode});
            addrParts.add(new String[]{"COUNTRY",  country});
            if (rand.nextBoolean()) addrParts.add(new String[]{"LANDMARK", landmark});

            for (String[] part : addrParts) {
                CustomerAddress ca = new CustomerAddress();
                Timestamp aHost = randomTs();
                Timestamp aAcpt = new Timestamp(aHost.getTime() + rand.nextInt(300) * 1000L);
                ca.setCustomerIdentifier(custId);
                ca.setCustomerNameComponentType(part[0]);   // address type
                ca.setCustomerNameComponentValue(part[1]);  // address value
                ca.setEffectiveDate(randomDate(2018, 2024));
                ca.setCrudValue(pickCrud());
                ca.setUserID(pick(USER_IDS));
                ca.setWorkstationID(pick(WORKSTATIONS));
                ca.setProgramID(pick(PROGRAMS));
                ca.setAcceptanceTimestamp(aAcpt);
                ca.setAcceptanceTimestampUTCoffset(utcOffsetTs(aAcpt));
                ca.setUuid(uuid());
                addressRepo.save(ca);
            }

            // ── 4. CustomerContactDetails ─────────────────────────────────────
            // One contact per customer; alternates between phone and email
            CustomerContactDetails cc = new CustomerContactDetails();
            Timestamp ccHost = randomTs();
            Timestamp ccAcpt = new Timestamp(ccHost.getTime() + rand.nextInt(300) * 1000L);
            String contactValue = rand.nextBoolean() ? phone() : email(firstName, lastName);
            cc.setCustomerIdentification(savedCd);
            cc.setCustomerContactDetails(contactValue);
            cc.setStartDate(randomDate(2018, 2022));
            cc.setEndDate(rand.nextInt(5) == 0 ? randomDate(2022, 2025) : null); // occasional end date
            cc.setCrudValue(pickCrud());
            cc.setUserID(pick(USER_IDS));
            cc.setWorkstationID(pick(WORKSTATIONS));
            cc.setProgramID(pick(PROGRAMS));
            cc.setAcceptanceTimestamp(ccAcpt);
            cc.setAcceptanceTimestampUTCoffset(utcOffsetTs(ccAcpt));
            cc.setUuid(uuid());
            contactRepo.save(cc);

            // ── 5. CustomerIdentification — 1 or 2 ID docs ───────────────────
            List<String> usedIdTypes = new ArrayList<>();
            int idCount = 1 + rand.nextInt(2); // 1 or 2
            for (int j = 0; j < idCount; j++) {
                String idType;
                do { idType = pick(ID_TYPES); } while (usedIdTypes.contains(idType));
                usedIdTypes.add(idType);

                String idItem;
                switch (idType) {
                    case "AADHAAR":          idItem = aadhaar(); break;
                    case "PAN":              idItem = pan(); break;
                    case "PASSPORT":         idItem = passport(); break;
                    case "VOTER_ID":         idItem = "KA/" + (rand.nextInt(900000) + 100000); break;
                    case "DRIVING_LICENSE":  idItem = "KA" + String.format("%02d", rand.nextInt(30)+1) + " " + (rand.nextInt(9000000)+1000000); break;
                    case "GSTIN":            idItem = String.format("%02d", rand.nextInt(35)+1) + pan().substring(0,10) + "1ZS"; break;
                    default:                 idItem = "ID-" + (rand.nextInt(900000) + 100000);
                }

                CustomerIdentification ci = new CustomerIdentification();
                Timestamp iHost = randomTs();
                Timestamp iAcpt = new Timestamp(iHost.getTime() + rand.nextInt(300) * 1000L);
                ci.setCustomerIdentificationType(idType);
                ci.setCustomerIdentificationItem(idItem);
                ci.setEffectiveDate(randomDate(2015, 2024));
                ci.setCrudValue(pickCrud());
                ci.setUserID(pick(USER_IDS));
                ci.setWorkstationID(pick(WORKSTATIONS));
                ci.setProgramID(pick(PROGRAMS));
                ci.setAcceptanceTimestamp(iAcpt);
                ci.setAcceptanceTimestampUTCoffset(utcOffsetTs(iAcpt));
                ci.setUuid(uuid());
                identificationRepo.save(ci);
            }

            // ── 6. CustomerClassification ─────────────────────────────────────
            CustomerClassification clf = new CustomerClassification();
            Timestamp clfHost = randomTs();
            Timestamp clfAcpt = new Timestamp(clfHost.getTime() + rand.nextInt(300) * 1000L);
            clf.setCustomerClassificationType(pick(CLASS_TYPES));
            clf.setCustomerClassificationTypeValue(pick(CLASS_VALUES));
            clf.setEffectiveDate(randomDate(2018, 2024));
            clf.setCrudValue(pickCrud());
            clf.setUserID(pick(USER_IDS));
            clf.setWorkstationID(pick(WORKSTATIONS));
            clf.setProgramID(pick(PROGRAMS));
            clf.setAcceptanceTimestamp(clfAcpt);
            clf.setAcceptanceTimestampUTCoffset(utcOffsetTs(clfAcpt));
            clf.setUuid(uuid());
            classificationRepo.save(clf);

            // ── 7. CustomerProofOfIdentity ────────────────────────────────────
            CustomerProofOfIdentity poi = new CustomerProofOfIdentity();
            Timestamp pHost = randomTs();
            Timestamp pAcpt = new Timestamp(pHost.getTime() + rand.nextInt(300) * 1000L);
            poi.setCustomerIdentifier(custId);
            poi.setCustomerClassificationTypeValue(pick(PROOF_TYPES));
            poi.setStartDate(randomDate(2015, 2022));
            poi.setEndDate(randomDate(2025, 2035)); // document expiry
            poi.setCrudValue(pickCrud());
            poi.setUserID(pick(USER_IDS));
            poi.setWorkstationID(pick(WORKSTATIONS));
            poi.setProgramID(pick(PROGRAMS));
            poi.setAcceptanceTimestamp(pAcpt);
            poi.setAcceptanceTimestampUTCoffset(utcOffsetTs(pAcpt));
            poi.setUuid(uuid());
            proofRepo.save(poi);

            System.out.printf("[DataSeeder] (%d/%d) Seeded customer: %s %s (id=%d)%n",
                    i + 1, totalCustomers, firstName, lastName, custId);
        }

        System.out.println("[DataSeeder] ✅ Done! Seeded " + totalCustomers + " customers across all 7 tables.");
    }
}
