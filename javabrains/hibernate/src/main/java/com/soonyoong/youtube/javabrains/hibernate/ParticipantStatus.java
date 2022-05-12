package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public enum ParticipantStatus {
    DUMMY("D"),
    BEFORE_STARTING("BS"),
    STARTED("S"),
    ENROLLED("E"),
    COMPLETED("C"),
    DEPOSITED("DP"),
    REWARDED("R"),
    REQUEST_FOR_REDEEM("Q"),
    REDEEMED("RE"),
    ENDED("X"),
    INVALID("I"),
    HOLD("H"),
    ERROR("ER"),
    REFEREE_ACCOUNT_OPENED("RO"),
    REFEREE_DEPOSITED("RD"),
    REFEREE_TRADED("RT"),
    VOUCHER_REDEEMABLE("VR"),
    EXPIRED("EP"),
    REFEREE_REDEEMED("RR"),
    REFEREE_REQUEST_FOR_REDEEM("RQ"),
    WELCOME("W"),
    OPT_OUT("OO")
    ;



    public  static final Set<ParticipantStatus> TERMINATE_STATUSES = new HashSet<>(Arrays.asList(ENDED, INVALID, HOLD, ERROR, EXPIRED));
    private  static final Map<ParticipantStatus, ParticipantStatus> NEXT_MAP = new EnumMap<>(ParticipantStatus.class);

    static {
        ParticipantStatus[] values = ParticipantStatus.values();
        for (int i = 0; i < values.length - 1; i++) {
            NEXT_MAP.put(values[i], values[i + 1]);
        }
    }

    private final String code;

    ParticipantStatus(String code) {
        this.code = code;
    }

    public static ParticipantStatus next(ParticipantStatus status) {
        return NEXT_MAP.get(status);
    }

    public static boolean isATerminatedStatus(ParticipantStatus status) {
        return TERMINATE_STATUSES.contains(status);
    }

    public static boolean isQualified(ParticipantStatus status) {
        if (status == null) {
            return false;
        }

        return status.compareTo(REWARDED) >= 0 && status.compareTo(ENDED) <= 0;
    }

    public static boolean isEnded(ParticipantStatus participantStatus) {
        return participantStatus == ENDED;
    }

    public boolean isInvalid() {
        return INVALID.equals(this) || ERROR.equals(this);
    }

    public boolean isValid() {
        return !isInvalid();
    }

}
