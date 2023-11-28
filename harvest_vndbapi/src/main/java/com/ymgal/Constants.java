package com.ymgal;

public class Constants {
    //          Dump info  .
    public static final String TagsDump = "https://vndb.org/api/tags.json.gz";
    public static final char EotChar = 0x04;
    public static final String ApiDomain = "api.vndb.org";
    public static final Integer ApiPort = 19534;
    public static final Integer ApiPortTls = 19535;
    public static final String TraitsDump = "https://vndb.org/api/traits.json.gz";
    public static final String VotesDump = "https://vndb.org/api/votes.gz";
    public static final String VotesDump2 = "https://vndb.org/api/votes2.gz";

    //Login
    public static final String ClientName = "AwesomeClient";
    public static final String ClientVersion = "1.0";
    //          Misc commands  .
    public static final String LoginCommand = "login";
    public static final String DbStatsCommand = "dbstats";


    //          Get commands  .
    public static final String GetVisualNovelCommand = "get vn";
    public static final String GetReleaseCommand = "get release";
    public static final String GetProducerCommand = "get producer";
    public static final String GetCharacterCommand = "get character";
    public static final String GetUserCommand = "get user";
    public static final String GetVotelistCommand = "get votelist";
    public static final String GetVisualNovelListCommand = "get vnlist";
    public static final String GetWishlistCommand = "get wishlist";
    public static final String GetUserListCommand = "get ulist";
    public static final String GetUserListLabelsCommand = "get ulist-labels";
    public static final String GetStaffCommand = "get staff";


    //          Set commands  .
    public static final String SetVotelistCommand = "set votelist";
    public static final String SetVisualNovelListCommand = "set vnlist";
    public static final String SetWishlistCommand = "set wishlist";
    public static final String SetUserListCommand = "set ulist";


    // Result values  .
    public static final String Results = "results";
    public static final String DbStats = "dbstats"; // Yes, this is identical to DbStatsCommand.
    public static final String Error = "error";
    public static final String Ok = "ok";

}

