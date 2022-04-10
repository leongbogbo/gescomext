package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.Site;

public interface SiteService {
	Site saveSite(Site elmt);
	Site updateSite(Site elmt);
	void deleteSite(Site elmt);
	void deleteSiteById(Long id);
	Site getSiteById(Long id);
	List<Site> getAllSite();
	Site findByNomSite(String nomSite);
}
