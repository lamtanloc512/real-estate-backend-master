package com.devcamp.real_estate_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.District;
import com.devcamp.real_estate_backend.model.Ward;
import com.devcamp.real_estate_backend.repository.DistrictRepository;
import com.devcamp.real_estate_backend.repository.WardRepository;

@Service
public class WardService {
    @Autowired
    WardRepository wardRepository;

    @Autowired
    DistrictRepository districtRepository;

    public Ward getWardById(Long id){
        Optional<Ward> wardFounded = wardRepository.findById(id.intValue());
        if(wardFounded.isPresent()){
            return wardFounded.get();
        }
        else{
            return null;
        }
    }

    public Ward createWard(String districtName, String provinceName, Ward wardJson){
        List<District> districts = districtRepository.findByName(districtName);
        if(districts != null && !districts.isEmpty()){
            // Filter districts by province
            District matchingDistrict = districts.stream()
                                                .filter(d -> d.getProvince() != null && d.getProvince().getName().equalsIgnoreCase(provinceName))
                                                .findFirst()
                                                .orElse(null);
            if(matchingDistrict != null){
                wardJson.setDistrict(matchingDistrict);

                // Set the province explicitly
                wardJson.setProvince(matchingDistrict.getProvince());
                
                Ward savedWard = wardRepository.save(wardJson);
                return savedWard;
            }
        }
        return null;
    }

    public Ward updateWard(Long id, Ward wardJson){
        Optional<Ward> foundedWard = wardRepository.findById(id.intValue());
        if(foundedWard.isPresent()){
            Ward _ward = foundedWard.get();
            _ward.setName(wardJson.getName());
            _ward.setPrefix(wardJson.getPrefix());

            wardRepository.save(_ward);
            return _ward;
        }
        else{
            return null;
        }
    }

    public String deleteWardById(Long id){
        if(wardRepository.findById(id.intValue()).isPresent()){
            wardRepository.deleteById(id.intValue());
            return "Ward deleted successfully";
        }
        return null;
    }

}
