package com.devcamp.real_estate_backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.District;
import com.devcamp.real_estate_backend.model.Province;
import com.devcamp.real_estate_backend.model.Ward;
import com.devcamp.real_estate_backend.repository.DistrictRepository;
import com.devcamp.real_estate_backend.repository.ProvinceRepository;

@Service
public class DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    ProvinceRepository ProvinceRepository;

    public List<Ward> getWardByDistrictName(String name){
        List<District> districtList = districtRepository.findByName(name);
        List<Ward> allWards = new ArrayList<>();
        if(districtList != null && !districtList.isEmpty()){
            for(District district : districtList){
                if(district.getWards() != null){
                    allWards.addAll(district.getWards());
                }
            }
        }
        return allWards.isEmpty() ? Collections.emptyList() : allWards;
    }

    public List<Ward> getWardsByDistrictNameAndProvince(String name, String provinceName){
        List<District> districts = districtRepository.findByNameAndProvince(name, provinceName);
        List<Ward> allWards = new ArrayList<>();

        if (districts != null && !districts.isEmpty()) {
            for (District district : districts) {
                if (district.getWards() != null) {
                    allWards.addAll(district.getWards());
                }
            }
        }
        return allWards.isEmpty() ? Collections.emptyList() : allWards;
    }

    public District getDistrictById(Long id){
        Optional<District> districtFounded = districtRepository.findById(id.intValue());
        if(districtFounded.isPresent()){
            return districtFounded.get();
        }
        else{
            return null;
        }
    }

    public District createDistrict(String provinceCode, District districtJson){
        Optional<Province> province = Optional.of(ProvinceRepository.findByCode(provinceCode));
        if(province != null){
            districtJson.setProvince(province.get());

            District saveDistrict = districtRepository.save(districtJson);
            return saveDistrict;
        }
        else{
            return null;
        }
    }

    public District updateDistrict(Long id, District districtJson){
        Optional<District> _districtData = districtRepository.findById(id.intValue());
        if(_districtData.isPresent()){
            District _district = _districtData.get();
            _district.setName(districtJson.getName());
            _district.setPrefix(districtJson.getPrefix());

            districtRepository.save(_district);
            return _district;
        }
        return null;
    }

    public String deleteDistrictById(Long id){
        if(districtRepository.findById(id.intValue()).isPresent()){
            districtRepository.deleteById(id.intValue());
            return "District deleted successfully";
        }
        return null;
    }
}
