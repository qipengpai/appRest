package com.company.platform.restapi.service.modelmanager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.company.platform.restapi.dao.loan.LoanTempMapper;
import com.company.platform.restapi.dao.modelmanager.ImgAndDocModelMapper;
import com.company.platform.restapi.model.loan.handled.ImageDataInfo;
import com.company.platform.restapi.model.loan.handled.ImageInfo;
import com.company.platform.restapi.model.modelmanager.ImgAndDocModel;
import com.company.platform.restapi.model.modelmanager.ImgAndDocTitle;
import com.company.platform.restapi.model.modelmanager.ImgInfoModel;
import com.company.platform.restapi.service.modelmanager.IImgAndDocModelService;

/** 
* @ClassName: ImgAndDocModelServiceImpl 
* @Description: TODO(操作影像模型service实现) 
* @author luyuchi
 * @param <LoanProductApplyModelV>
* @date 2018年1月29日 下午12:57:35 
*  
*/
@Service
public class ImgAndDocModelServiceImpl implements IImgAndDocModelService {

	@Autowired
	private ImgAndDocModelMapper imgAndDocVersionModelMapper;

	@Autowired
	private LoanTempMapper loanTempMapper;

	@Value("${image.app_ip}")
	private String appIp;

	@Override
	public List<ImgInfoModel> getImgInfoModelsByProductId(String productId) {
		return imgAndDocVersionModelMapper.getImgInfoModelsByProductId(productId);
	}

	@Override
	public ImgAndDocModel getImgAndDocModelById(String id) {
		return imgAndDocVersionModelMapper.getImgAndDocModelById(id);
	}

	@Override
	public ImgAndDocTitle getImgAndDocTitleById(String id) {
		return imgAndDocVersionModelMapper.getImgAndDocTitleById(id);
	}

	@Override
	public String getMaterialDataId(String businessId) {
		return imgAndDocVersionModelMapper.getMaterialDataId(businessId);
	}

	@Override
	public String saveMaterialData(String id, String modelId, String businessId, String extension, String fileName,
			String modelCode, String classCode, String path, String orgId, String share) {
		String dataId = imgAndDocVersionModelMapper.getMaterialDataId(businessId);
		if (dataId == null) {
			dataId = UUID.randomUUID().toString();
			int i = imgAndDocVersionModelMapper.saveMaterialDataForRecord(dataId, modelId, businessId, "影像资料附件集",
					new Date());
			if (i == 0) {
				dataId = null;
			}
		}
		if (dataId != null) {
			imgAndDocVersionModelMapper.saveMaterialData(id, dataId, extension, modelCode, classCode, fileName, path, orgId,
					share);
		}
		return dataId;
	}

	@Override
	public void addMaterialData(String id, String dataId, String extension, String fileName, String modelCode, String classCode,
			String path, String orgId, String share) {
		imgAndDocVersionModelMapper.saveMaterialData(id, dataId, extension, modelCode, classCode, fileName, path, orgId,
				share);
	}

	@Override
	public Map<String, Object> getFileInfoByModelInfo(String loanProductApplyId, String classId, String id) {
		return imgAndDocVersionModelMapper.getFileInfoByModelInfo(loanProductApplyId, classId, id);
	}

	@Override
	public int deleteFileInfo(String id) {
		return imgAndDocVersionModelMapper.deleteFileInfoById(id);
	}

	@Override
	public List<ImageDataInfo> getImageInfoList(String loanApplyId) {
		List<ImageDataInfo> data = new ArrayList<ImageDataInfo>();
		Map<String, Object> imgRecord = loanTempMapper.getImgRecordByBusId(loanApplyId);
		if (imgRecord != null) {
			List<ImageInfo> imageList = loanTempMapper.selectImageInfoByBusinessId(loanApplyId);
			if (imageList != null && imageList.size() > 0) {
				for (ImageInfo image : imageList) {
					ImageDataInfo item = new ImageDataInfo();
					item.setClassId(image.getClassId());
					String url = appIp + "/wechat/showImage?filePath=" + image.getFilePath() + "&fileName="
							+ image.getFileName();
					item.setUrl(url);
					item.setId(image.getId());
					data.add(item);
				}
			}
		}
		return data;
	}
	
	@Override
	public List<ImgAndDocTitle> getImgAndDocTitleInfo(String loanApplyId) {
		List<ImgAndDocTitle> data = new ArrayList<ImgAndDocTitle>();
		Map<String, Object> imgRecord = loanTempMapper.getImgRecordByBusId(loanApplyId);
		if (imgRecord != null) {
			//通过modelId获取model下classId和className
			data = loanTempMapper.getImgAndDocTitleInfoByModelId(imgRecord.get("modelId").toString());
			/*List<ImageInfo> imageList = loanTempMapper.selectImageInfoByBusinessId(loanApplyId);
			if (imageList != null && imageList.size() > 0) {
				for (ImageInfo image : imageList) {
					ImageDataInfo item = new ImageDataInfo();
					item.setClassId(image.getClassId());
					String url = appIp + "/image/showImage?filePath=" + image.getFilePath() + "&fileName="
							+ image.getFileName();
					item.setUrl(url);
					item.setId(image.getId());
					data.add(item);
				}
			}*/
		}
		return data;
	}
	
	@Override
	public String getMaterialDataIdPro(String modelId, String businessId) {
		return loanTempMapper.findMaterialDataId(modelId, businessId);
	}
	
	@Override
	public int imageClassNum(String classId,String recordId) {
		ImgAndDocTitle title = loanTempMapper.queryModelClass(classId);
		if(title.getSize()>0){
			List<Map<String,Object>> list = loanTempMapper.queryFilesData(recordId, title.getCode());
			if(list!=null&&list.size()>0){
				return title.getSize()-list.size();
			}else{
				return title.getSize();
			}
			
		}else{
			//无限制
			return 999999;
		}

	}
}
