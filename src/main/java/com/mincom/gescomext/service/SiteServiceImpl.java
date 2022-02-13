package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Site;
import com.mincom.gescomext.repository.SiteRepository;

@Service
public class SiteServiceImpl implements SiteService {
	
	@Autowired
	SiteRepository siteRepository;

	@Override
	public Site saveSite(Site elmt) {
		return siteRepository.save(elmt);
	}

	@Override
	public Site updateSite(Site elmt) {
		return siteRepository.save(elmt);
	}

	@Override
	public void deleteSite(Site elmt) {
		siteRepository.delete(elmt);		
	}

	@Override
	public void deleteSiteById(Long id) {
		siteRepository.deleteById(id);		
	}

	@Override
	public Site getSiteById(Long id) {
		return siteRepository.findById(id).get();
	}

	@Override
	public List<Site> getAllSite() {
		return siteRepository.findAll();
	}

}
