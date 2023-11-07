package com.briup.framework.service.impl;

import com.briup.framework.bean.extend.ColdStorageRecordExtend1;
import com.briup.framework.bean.extend.DiseaseRecordExtend;
import com.briup.framework.bean.extend.FeedRecordExtend;
import com.briup.framework.bean.extend.IndexRecordExtend;
import com.briup.framework.bean.extend.InoculationRecordExtend;
import com.briup.framework.bean.extend.IssueRecordExtend;
import com.briup.framework.bean.extend.ManagerAnimalExtend;
import com.briup.framework.bean.extend.OutCircleExtend;
import com.briup.framework.bean.extend.ProductSource;
import com.briup.framework.bean.extend.QuarantineRegistrationExtend;
import com.briup.framework.bean.extend.ShiftCircleExtend;
import com.briup.framework.bean.extend.SlaughterRegistrationExtend;
import com.briup.framework.mapper.extend.BatchAnimalMapper;
import com.briup.framework.mapper.extend.ColdStorageRecordExtendMapper;
import com.briup.framework.mapper.extend.DiseaseRecordExtendMapper;
import com.briup.framework.mapper.extend.FeedRecordExtendMapper;
import com.briup.framework.mapper.extend.IndexRecordExtendMapper;
import com.briup.framework.mapper.extend.InoculationRecordExtendMapper;
import com.briup.framework.mapper.extend.IssueRecordExtendMapper;
import com.briup.framework.mapper.extend.ManagerAnimalExtendMapper;
import com.briup.framework.mapper.extend.OutCircleExtendMapper;
import com.briup.framework.mapper.extend.QuarantineRegistrationExtendMapper;
import com.briup.framework.mapper.extend.ShiftCircleExtendMapper;
import com.briup.framework.mapper.extend.SlaughterRegistrationExtendMapper;
import com.briup.framework.service.IProjectSourceService;
import com.briup.framework.utils.execption.BriupFrameworkException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pgc
 * @Description: 溯源业务层
 * @date 2022/4/11 14:08
 */
@Service
public class ProjectSourceServiceImpl implements IProjectSourceService {
    @Autowired
    BatchAnimalMapper batchAnimalMapper;
   @Autowired
    ManagerAnimalExtendMapper managerAnimalExtendMapper;
   @Autowired
    ShiftCircleExtendMapper shiftCircleExtendMapper;
   @Autowired
    InoculationRecordExtendMapper inoculationRecordExtendMapper;
   @Autowired
    FeedRecordExtendMapper feedRecordExtendMapper;
   @Autowired
    DiseaseRecordExtendMapper diseaseRecordExtendMapper;
   @Autowired
    IndexRecordExtendMapper indexRecordExtendMapper;
   @Autowired
   QuarantineRegistrationExtendMapper quarantineRegistrationExtendMapper;
   @Autowired
    OutCircleExtendMapper outCircleExtendMapper;
   @Autowired
    SlaughterRegistrationExtendMapper slaughterRegistrationExtendMapper;
    @Autowired
    ColdStorageRecordExtendMapper coldStorageRecordExtendMapper;
    @Autowired
    IssueRecordExtendMapper issueRecordExtendMapper;
    @Override
    public void updateAnimalImg(String animalId, String fileImg) {
        batchAnimalMapper.updateAnimalImg(animalId,fileImg);
    }

    @Override
    public ProductSource query(String animalId) {
        ProductSource source = new ProductSource();
        //存放基本信息
        ManagerAnimalExtend animal = managerAnimalExtendMapper.findByAnimalId(animalId);
        if (animal==null){
            throw new BriupFrameworkException("该动物不存在");
        }
        source.setManagerAnimalExtend(animal);
        if (animal.getaStatus().equals("已销售")) {
            //移圈历史
            List<ShiftCircleExtend> shiftCircleExtends = shiftCircleExtendMapper.query(animalId, null, null, null, null);
            source.setShiftCircleExtends(shiftCircleExtends);
            //接种记录
            List<InoculationRecordExtend> inoculationRecordExtends = inoculationRecordExtendMapper.selectWithExtend(animalId, null, null, null, null);
            source.setInoculationRecordExtends(inoculationRecordExtends);
            //饲料喂养
            List<FeedRecordExtend> feedRecordExtends = feedRecordExtendMapper.query(animal.getaBatchId(), null);
            source.setFeedRecordExtends(feedRecordExtends);
            //病症记录
            List<DiseaseRecordExtend> diseaseRecordExtends = diseaseRecordExtendMapper.query(animalId, null, null, null, null, null);
            source.setDiseaseRecordExtends(diseaseRecordExtends);
            //指标记录
            List<IndexRecordExtend> indexRecordExtends = indexRecordExtendMapper.query(animalId, null, null, null, null, null, null, null, null, null, null);
            source.setIndexRecordExtends(indexRecordExtends);
            //检疫阶段
            List<QuarantineRegistrationExtend> quarantineRegistrationExtends = quarantineRegistrationExtendMapper.query(animal.getaBatchId(), null, null, null, null, null);
            if (quarantineRegistrationExtends != null && quarantineRegistrationExtends.size() > 0) {
                source.setQuarantineRegistrationExtend(quarantineRegistrationExtends.get(0));
            } else {
                source.setQuarantineRegistrationExtend(null);
            }
            //出栏阶段
            OutCircleExtend outCircleExtend = outCircleExtendMapper.queryByBatchId(animal.getaBatchId());
            source.setOutCircleExtend(outCircleExtend);
            //屠宰阶段
            List<SlaughterRegistrationExtend> slaughterRegistrationExtends = slaughterRegistrationExtendMapper.selectRecordWithSGroupByBatchId(animal.getaBatchId());
            if (slaughterRegistrationExtends != null && slaughterRegistrationExtends.size() > 0) {
                source.setSlaughterRegistrationExtend(slaughterRegistrationExtends.get(0));
            } else {
                source.setQuarantineRegistrationExtend(null);
            }
            //仓储阶段
            ColdStorageRecordExtend1 coldStorageRecordExtend1 = coldStorageRecordExtendMapper.queryByAnimalId(animalId);
            source.setColdStorageRecordExtend1(coldStorageRecordExtend1);
            //销售阶段
            List<IssueRecordExtend> ssueRecordExtends = issueRecordExtendMapper.selectById(Integer.parseInt(animal.getaBackup1()));
            if (ssueRecordExtends != null && ssueRecordExtends.size() > 0) {
                source.setIssueRecordExtend(ssueRecordExtends.get(0));
            } else {
                source.setIssueRecordExtend(null);
            }
        }else if(animal.getaStatus().equals("已入库")){
            //移圈历史
            List<ShiftCircleExtend> shiftCircleExtends = shiftCircleExtendMapper.query(animalId, null, null, null, null);
            source.setShiftCircleExtends(shiftCircleExtends);
            //接种记录
            List<InoculationRecordExtend> inoculationRecordExtends = inoculationRecordExtendMapper.selectWithExtend(animalId, null, null, null, null);
            source.setInoculationRecordExtends(inoculationRecordExtends);
            //饲料喂养
            List<FeedRecordExtend> feedRecordExtends = feedRecordExtendMapper.query(animal.getaBatchId(), null);
            source.setFeedRecordExtends(feedRecordExtends);
            //病症记录
            List<DiseaseRecordExtend> diseaseRecordExtends = diseaseRecordExtendMapper.query(animalId, null, null, null, null, null);
            source.setDiseaseRecordExtends(diseaseRecordExtends);
            //指标记录
            List<IndexRecordExtend> indexRecordExtends = indexRecordExtendMapper.query(animalId, null, null, null, null, null, null, null, null, null, null);
            source.setIndexRecordExtends(indexRecordExtends);
            //检疫阶段
            List<QuarantineRegistrationExtend> quarantineRegistrationExtends = quarantineRegistrationExtendMapper.query(animal.getaBatchId(), null, null, null, null, null);
            if (quarantineRegistrationExtends != null && quarantineRegistrationExtends.size() > 0) {
                source.setQuarantineRegistrationExtend(quarantineRegistrationExtends.get(0));
            } else {
                source.setQuarantineRegistrationExtend(null);
            }
            //出栏阶段
            OutCircleExtend outCircleExtend = outCircleExtendMapper.queryByBatchId(animal.getaBatchId());
            source.setOutCircleExtend(outCircleExtend);
            //屠宰阶段
            List<SlaughterRegistrationExtend> slaughterRegistrationExtends = slaughterRegistrationExtendMapper.selectRecordWithSGroupByBatchId(animal.getaBatchId());
            if (slaughterRegistrationExtends != null && slaughterRegistrationExtends.size() > 0) {
                source.setSlaughterRegistrationExtend(slaughterRegistrationExtends.get(0));
            } else {
                source.setQuarantineRegistrationExtend(null);
            }
            //仓储阶段
            ColdStorageRecordExtend1 coldStorageRecordExtend1 = coldStorageRecordExtendMapper.queryByAnimalId(animalId);
            source.setColdStorageRecordExtend1(coldStorageRecordExtend1);
        }else if (animal.getaStatus().equals("已屠宰")){
            //移圈历史
            List<ShiftCircleExtend> shiftCircleExtends = shiftCircleExtendMapper.query(animalId, null, null, null, null);
            source.setShiftCircleExtends(shiftCircleExtends);
            //接种记录
            List<InoculationRecordExtend> inoculationRecordExtends = inoculationRecordExtendMapper.selectWithExtend(animalId, null, null, null, null);
            source.setInoculationRecordExtends(inoculationRecordExtends);
            //饲料喂养
            List<FeedRecordExtend> feedRecordExtends = feedRecordExtendMapper.query(animal.getaBatchId(), null);
            source.setFeedRecordExtends(feedRecordExtends);
            //病症记录
            List<DiseaseRecordExtend> diseaseRecordExtends = diseaseRecordExtendMapper.query(animalId, null, null, null, null, null);
            source.setDiseaseRecordExtends(diseaseRecordExtends);
            //指标记录
            List<IndexRecordExtend> indexRecordExtends = indexRecordExtendMapper.query(animalId, null, null, null, null, null, null, null, null, null, null);
            source.setIndexRecordExtends(indexRecordExtends);
            //检疫阶段
            List<QuarantineRegistrationExtend> quarantineRegistrationExtends = quarantineRegistrationExtendMapper.query(animal.getaBatchId(), null, null, null, null, null);
            if (quarantineRegistrationExtends != null && quarantineRegistrationExtends.size() > 0) {
                source.setQuarantineRegistrationExtend(quarantineRegistrationExtends.get(0));
            } else {
                source.setQuarantineRegistrationExtend(null);
            }
            //出栏阶段
            OutCircleExtend outCircleExtend = outCircleExtendMapper.queryByBatchId(animal.getaBatchId());
            source.setOutCircleExtend(outCircleExtend);
            //屠宰阶段
            List<SlaughterRegistrationExtend> slaughterRegistrationExtends = slaughterRegistrationExtendMapper.selectRecordWithSGroupByBatchId(animal.getaBatchId());
            if (slaughterRegistrationExtends != null && slaughterRegistrationExtends.size() > 0) {
                source.setSlaughterRegistrationExtend(slaughterRegistrationExtends.get(0));
            } else {
                source.setQuarantineRegistrationExtend(null);
            }
        }else if (animal.getaStatus().equals("已出栏")){
            //移圈历史
            List<ShiftCircleExtend> shiftCircleExtends = shiftCircleExtendMapper.query(animalId, null, null, null, null);
            source.setShiftCircleExtends(shiftCircleExtends);
            //接种记录
            List<InoculationRecordExtend> inoculationRecordExtends = inoculationRecordExtendMapper.selectWithExtend(animalId, null, null, null, null);
            source.setInoculationRecordExtends(inoculationRecordExtends);
            //饲料喂养
            List<FeedRecordExtend> feedRecordExtends = feedRecordExtendMapper.query(animal.getaBatchId(), null);
            source.setFeedRecordExtends(feedRecordExtends);
            //病症记录
            List<DiseaseRecordExtend> diseaseRecordExtends = diseaseRecordExtendMapper.query(animalId, null, null, null, null, null);
            source.setDiseaseRecordExtends(diseaseRecordExtends);
            //指标记录
            List<IndexRecordExtend> indexRecordExtends = indexRecordExtendMapper.query(animalId, null, null, null, null, null, null, null, null, null, null);
            source.setIndexRecordExtends(indexRecordExtends);
            //检疫阶段
            List<QuarantineRegistrationExtend> quarantineRegistrationExtends = quarantineRegistrationExtendMapper.query(animal.getaBatchId(), null, null, null, null, null);
            if (quarantineRegistrationExtends != null && quarantineRegistrationExtends.size() > 0) {
                source.setQuarantineRegistrationExtend(quarantineRegistrationExtends.get(0));
            } else {
                source.setQuarantineRegistrationExtend(null);
            }
            //出栏阶段
            OutCircleExtend outCircleExtend = outCircleExtendMapper.queryByBatchId(animal.getaBatchId());
            source.setOutCircleExtend(outCircleExtend);
        }else if (animal.getaStatus().equals("已检疫")){
            //移圈历史
            List<ShiftCircleExtend> shiftCircleExtends = shiftCircleExtendMapper.query(animalId, null, null, null, null);
            source.setShiftCircleExtends(shiftCircleExtends);
            //接种记录
            List<InoculationRecordExtend> inoculationRecordExtends = inoculationRecordExtendMapper.selectWithExtend(animalId, null, null, null, null);
            source.setInoculationRecordExtends(inoculationRecordExtends);
            //饲料喂养
            List<FeedRecordExtend> feedRecordExtends = feedRecordExtendMapper.query(animal.getaBatchId(), null);
            source.setFeedRecordExtends(feedRecordExtends);
            //病症记录
            List<DiseaseRecordExtend> diseaseRecordExtends = diseaseRecordExtendMapper.query(animalId, null, null, null, null, null);
            source.setDiseaseRecordExtends(diseaseRecordExtends);
            //指标记录
            List<IndexRecordExtend> indexRecordExtends = indexRecordExtendMapper.query(animalId, null, null, null, null, null, null, null, null, null, null);
            source.setIndexRecordExtends(indexRecordExtends);
            //检疫阶段
            List<QuarantineRegistrationExtend> quarantineRegistrationExtends = quarantineRegistrationExtendMapper.query(animal.getaBatchId(), null, null, null, null, null);
            if (quarantineRegistrationExtends != null && quarantineRegistrationExtends.size() > 0) {
                source.setQuarantineRegistrationExtend(quarantineRegistrationExtends.get(0));
            } else {
                source.setQuarantineRegistrationExtend(null);
            }
        }else if (animal.getaStatus().equals("养殖中")){
            //移圈历史
            List<ShiftCircleExtend> shiftCircleExtends = shiftCircleExtendMapper.query(animalId, null, null, null, null);
            source.setShiftCircleExtends(shiftCircleExtends);
            //接种记录
            List<InoculationRecordExtend> inoculationRecordExtends = inoculationRecordExtendMapper.selectWithExtend(animalId, null, null, null, null);
            source.setInoculationRecordExtends(inoculationRecordExtends);
            //饲料喂养
            List<FeedRecordExtend> feedRecordExtends = feedRecordExtendMapper.query(animal.getaBatchId(), null);
            source.setFeedRecordExtends(feedRecordExtends);
            //病症记录
            List<DiseaseRecordExtend> diseaseRecordExtends = diseaseRecordExtendMapper.query(animalId, null, null, null, null, null);
            source.setDiseaseRecordExtends(diseaseRecordExtends);
            //指标记录
            List<IndexRecordExtend> indexRecordExtends = indexRecordExtendMapper.query(animalId, null, null, null, null, null, null, null, null, null, null);
            source.setIndexRecordExtends(indexRecordExtends);
        }
        return source;
    }
}
