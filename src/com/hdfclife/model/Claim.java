package com.hdfclife.model;

public class Claim {
    private final String id;
    private final int amount;
    private final String policyId;
    private final String claimant;
    private final Urgency urgency;

    public Claim(String id, int amount, String policyId, String claimant, Urgency urgency) {
        this.id = id;
        this.amount = amount;
        this.policyId = policyId;
        this.claimant = claimant;
        this.urgency = urgency;
    }

    public String getId() { return id; }
    public int getAmount() { return amount; }
    public String getPolicyId() { return policyId; }
    public String getClaimant() { return claimant; }
    public Urgency getUrgency() { return urgency; }

    @Override
    public String toString() {
        return id;
    }
}
