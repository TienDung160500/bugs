package com.mycompany.myapp.web.rest.response;

public class DonViResponse {
    private Long id;
    private String donVi;

    public DonViResponse(Long id, String donVi) {
        this.id = id;
        this.donVi = donVi;
    }

    public DonViResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }
}
