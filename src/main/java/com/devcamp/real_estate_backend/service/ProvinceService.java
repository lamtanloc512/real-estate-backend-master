package com.devcamp.real_estate_backend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.District;
import com.devcamp.real_estate_backend.model.Province;
import com.devcamp.real_estate_backend.repository.ProvinceRepository;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    public List<Province> getAllProvinces(){
        List<Province> vProvinces = new ArrayList<>();
        provinceRepository.findAll().forEach(vProvinces::add);
        return vProvinces;
    }

    public List<Province> getProvincesPerPage(int page, int size){
        PageRequest pageWithElements = PageRequest.of(page, size);
        List<Province> listProvince = new ArrayList<>();
        provinceRepository.findAll(pageWithElements).forEach(listProvince::add);
        return listProvince;
    }

    public List<District> getDistrictByProvinceCode(String code){
        Province province = provinceRepository.findByCode(code);
        if(province != null){
            return new ArrayList<>(province.getDistricts());
        }
        return null;
    }

    public Province createProvince(Province provinceJson){
        Province _province = new Province(provinceJson.getCode(), provinceJson.getName());
        provinceRepository.save(_province);
        return _province;
    }

    public Province updateProvince(Long id, Province provinceJson){
        Optional<Province> _provinceData = provinceRepository.findById(id.intValue());
        if(_provinceData.isPresent()){
            Province _province = _provinceData.get();
            _province.setCode(provinceJson.getCode());
            _province.setName(provinceJson.getName());

            provinceRepository.save(_province);
            return _province;
        }
        return null;
    }

    public String deleteProvinceById(Long id){
        if(provinceRepository.findById(id.intValue()).isPresent()){
            provinceRepository.deleteById(id.intValue());
            return "Province deleted successfully";
        }
        return null;
    }
}
