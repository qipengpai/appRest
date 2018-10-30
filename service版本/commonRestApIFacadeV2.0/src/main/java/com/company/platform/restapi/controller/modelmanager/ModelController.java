/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller.modelmanager
 *
 *    Filename:    ModelController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2018
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.restapi.controller.modelmanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.loan.LoanProductApply;
import com.company.platform.restapi.model.loan.LoanProductApplyModelV;
import com.company.platform.restapi.model.loan.handled.ImageDataInfo;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoReq;
import com.company.platform.restapi.model.modelmanager.ImgAndDocModel;
import com.company.platform.restapi.model.modelmanager.ImgAndDocTitle;
import com.company.platform.restapi.model.modelmanager.UploadForImgReq;
import com.company.platform.restapi.service.loan.ILoanProductApplyModelService;
import com.company.platform.restapi.service.loan.ILoanProductApplyService;
import com.company.platform.restapi.service.loan.IProductApplyInfoService;
import com.company.platform.restapi.service.modelmanager.IImgAndDocModelService;
import com.company.platform.security.model.SecurityOrg;
import com.company.platform.security.model.SecurityUser;
import com.company.platform.security.service.WeixinCustomUserDetailsService;

/** 
* @ClassName: ModelController 
* @Description: TODO(模型操作集合，如：业务模型，影像模型等) 
* @author zhengjn 
* @date 2018年1月18日 上午9:41:10 
*  
*/
@RestController
@RequestMapping("wechat")
public class ModelController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(ModelController.class);
	
	@Value("${file.image_dir}")
	private String basePath; 
	
	@Value("${file.root_dir}")
	private String rootPath;
	
	/** 
	* @Fields number : TODO(保存到服务器文件名序列，防止文件名称重复) 
	*/ 
	private long number;
	
	@Autowired
	private IImgAndDocModelService imgAndDocModelService;
	
	@Autowired
	private ILoanProductApplyModelService loanProductApplyModelService;
	
	@Resource
	private ILoanProductApplyService loanProductApplyService;
	
	@Resource
    private WeixinCustomUserDetailsService weixinCustomUserDetailsService;
	
	/**  
	* @Title: uploadLoanMaterial
	* @Description: TODO(影像资料上传) 
	* @param req 接收app端传入参数 
	* @return BaseHttpParamsResp 返回给app端响应
	* @throws Exception
	*/  
	@RequestMapping(path = "/android_uploadLoanMaterial", method = RequestMethod.POST)
    @ResponseBody
    @RequestAccessAnnotation(modelName = "影像资料上传", modelType = RequestAccessConstants.SEARCH, desc = "上传影像资料方法")
	public BaseHttpParamsResp android_uploadLoanMaterial(String loanProductApplyId, String modelId, String classId, MultipartFile file, String orgId) throws Exception {
		String id = UUID.randomUUID().toString();
		logger.info("影像资料上传");
		BaseHttpParamsResp resp = new BaseHttpParamsResp();
		UploadForImgReq req = new UploadForImgReq();
			req.setId(id);
			req.setImgClassId(classId);
			req.setLoanProductApplyId(loanProductApplyId);
			req.setName(file.getOriginalFilename());
			//通过借贷申请id获取当前模型分类信息
			List<ImgAndDocTitle> imgAndDocTitle = imgAndDocModelService.getImgAndDocTitleInfo(loanProductApplyId);
			String dataId = imgAndDocModelService.getMaterialDataIdPro(imgAndDocTitle.get(0).getImgDocId().toString(), loanProductApplyId);
			for (ImgAndDocTitle imgAndDocTitle2 : imgAndDocTitle) {
				//获得文件可上传数量
				int imageClassNum =  imgAndDocModelService.imageClassNum(imgAndDocTitle2.getId().toString(),dataId);
				// 影像资料数据信息
				List<ImageDataInfo> imageInfoList = imgAndDocModelService.getImageInfoList(loanProductApplyId);
				int imageDataNum = 0;
				for (ImageDataInfo imageDataInfo : imageInfoList) {
					if(imageDataInfo.getClassId().equals(imgAndDocTitle2.getId().toString())){
						imageDataNum ++;
					}
				}
				if(imageClassNum > imageDataNum){
					continue;
				}else{
					resp = BaseHttpParamsResp.requestSuccess(ResponseConstants.MD_UPLOAD_MORE.getRetCode(), ResponseConstants.MD_UPLOAD_MORE.getRetMsg(),id);
					return resp;
				}
			}
			//通过借贷申请id获取借贷申请信息
			LoanProductApply loanProductApply= loanProductApplyService.selectLoanProductApplyById(loanProductApplyId);
			req.setLoanProductId(loanProductApply.getLoanProductId());
		    req.setOrgId(orgId);
					if(StringUtils.isBlank(req.getShare())) {
						req.setShare("1"); //设置默认 共享
					}
					
					ImgAndDocTitle imgAndDocTitle2 = imgAndDocModelService.getImgAndDocTitleById(req.getImgClassId());
					
					if(imgAndDocTitle2 != null) {
						ImgAndDocModel imgAndDocModel = imgAndDocModelService.getImgAndDocModelById(imgAndDocTitle2.getImgDocId());
						
						if(imgAndDocModel != null) {
							Calendar calendar = Calendar.getInstance();
							
							int year = calendar.get(Calendar.YEAR);
							
							int month = calendar.get(Calendar.MONTH);
							
							int day = calendar.get(Calendar.DAY_OF_YEAR);
							//在系统中文件名称
							String tempName = null;
							
							//String fileName = fileIo.getOriginalFilename();
							String fileName = req.getName();
							//生成文件上传路径
							StringBuilder path = new StringBuilder();
							//文件上传基本路径
							StringBuilder fileBasePath = new StringBuilder(this.rootPath + this.basePath); 
							
							path.append("/").append(imgAndDocModel.getCode());
							path.append("/").append(imgAndDocTitle2.getCode());
							path.append("/").append(year);
							path.append("/").append(month);
							path.append("/").append(day);
							
							synchronized(this) {
								tempName = String.valueOf(number);
								number++;
							}
							
							//获得文件扩展名
							String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
							tempName += ".";
							tempName += extension;
							
							File targetFile = new File(fileBasePath.append(path).toString());  
							
							if(!targetFile.exists()) {  
								targetFile.mkdirs();  
							}
							
							targetFile = new File(fileBasePath.toString(), tempName);
							
							if(!targetFile.exists()) {
								targetFile.createNewFile();
							}
							
							System.out.println("upload:" + targetFile.getPath());
							
							path.append("/").append(tempName);
							// 图片上传成功标记
							boolean flag = false;
							BufferedInputStream bin = null;
							BufferedOutputStream bos = null;
							
							try {
								bos = new BufferedOutputStream(new FileOutputStream(targetFile));
								bin = new BufferedInputStream(file.getInputStream());
								
								byte[] buffer = new byte[8 * 1024];
								int read = 0;
								while ((read = bin.read(buffer)) != -1) {
									bos.write(buffer, 0, read);
								}
								bos.flush();
								flag = true;
							} catch (Exception e) {
								resp.setExceptionFlag(ResponseConstants.REQUEST_FAIL.getRetCode());
								resp.setResponseCode(ResponseConstants.MD_UPLOAD_NONE.getRetCode());
								resp.setResponseMessage(ResponseConstants.MD_UPLOAD_NONE.getRetMsg());
								e.printStackTrace();
							} finally {
								if(bos != null) bos.close();
							}
							
							if (flag) {
								//fileIo.transferTo(targetFile);
								//获得影像记录ID
								String dataId2 = imgAndDocModelService.getMaterialDataId(req.getLoanProductApplyId());
								
								if(dataId2 == null) {
									dataId2 = imgAndDocModelService.saveMaterialData(req.getId(), imgAndDocTitle2.getImgDocId(), req.getLoanProductApplyId(), extension, fileName, imgAndDocModel.getCode(), imgAndDocTitle2.getCode(), path.toString(), orgId, req.getShare());
									if(dataId2 != null) {
										//保存业务与模型定义数据关联
										LoanProductApplyModelV loanProductApplyModelV = new LoanProductApplyModelV();
										loanProductApplyModelV.setLoanProductApplyId(req.getLoanProductApplyId());
										loanProductApplyModelV.setModelType("IMM");
										loanProductApplyModelV.setModelCode(imgAndDocModel.getCode());
										loanProductApplyModelV.setModelVersion(imgAndDocModel.getVersion());
										loanProductApplyModelV.setModelInstanceId(dataId2);
										loanProductApplyModelService.saveLoanProductApplyModelV(loanProductApplyModelV);
									}
								} else {
									imgAndDocModelService.addMaterialData(req.getId(), dataId2, extension, fileName, imgAndDocModel.getCode(), imgAndDocTitle2.getCode(), path.toString(), orgId, req.getShare());
								}
								resp = BaseHttpParamsResp.requestSuccess(ResponseConstants.REQUEST_SUCCESS.getRetCode(), ResponseConstants.MD_UPLOAD_SUCCESS.getRetMsg(),id);
							}
							
						} else {
							resp.setExceptionFlag(ResponseConstants.REQUEST_FAIL.getRetCode());
							resp.setResponseCode(ResponseConstants.MD_UPLOAD_CFGNOTFOUND.getRetCode());
							resp.setResponseMessage(ResponseConstants.MD_UPLOAD_CFGNOTFOUND.getRetMsg());
						}
					} else {
						resp.setExceptionFlag(ResponseConstants.REQUEST_FAIL.getRetCode());
						resp.setResponseCode(ResponseConstants.MD_UPLOAD_CFGNOTFOUND.getRetCode());
						resp.setResponseMessage(ResponseConstants.MD_UPLOAD_CFGNOTFOUND.getRetMsg());
					}
		return resp;
	}
	
	/**  
	* @Title: uploadLoanMaterial
	* @Description: TODO(影像资料上传) 
	* @param req 接收app端传入参数 
	* @return BaseHttpParamsResp 返回给app端响应
	* @throws Exception
	*/  
	@RequestMapping(path = "/ios_uploadLoanMaterial", method = RequestMethod.POST)
    @ResponseBody
    @RequestAccessAnnotation(modelName = "影像资料上传", modelType = RequestAccessConstants.SEARCH, desc = "上传影像资料方法")
	public BaseHttpParamsResp ios_uploadLoanMaterial(String loanProductApplyId, String modelId, String classId, String id, MultipartFile file, String orgId) throws Exception {
		logger.info("影像资料上传");
		BaseHttpParamsResp resp = new BaseHttpParamsResp();
		UploadForImgReq req = new UploadForImgReq();
			req.setId(id);
			req.setImgClassId(classId);
			req.setLoanProductApplyId(loanProductApplyId);
			req.setName(file.getOriginalFilename());
			//通过借贷申请id获取当前模型分类信息
			List<ImgAndDocTitle> imgAndDocTitle = imgAndDocModelService.getImgAndDocTitleInfo(loanProductApplyId);
			String dataId = imgAndDocModelService.getMaterialDataIdPro(imgAndDocTitle.get(0).getImgDocId().toString(), loanProductApplyId);
			for (ImgAndDocTitle imgAndDocTitle2 : imgAndDocTitle) {
				//获得文件可上传数量
				int imageClassNum =  imgAndDocModelService.imageClassNum(imgAndDocTitle2.getId().toString(),dataId);
				//获取现有针对于影像分类上传的文件的数量
				// 影像资料数据信息
				List<ImageDataInfo> imageInfoList = imgAndDocModelService.getImageInfoList(loanProductApplyId);
				int imageDataNum = 0;
				for (ImageDataInfo imageDataInfo : imageInfoList) {
					if(imageDataInfo.getClassId().equals(imgAndDocTitle2.getId().toString())){
						imageDataNum ++;
					}
				}
				if(imageClassNum > imageDataNum){
					continue;
				}else{
					resp = BaseHttpParamsResp.requestSuccess(ResponseConstants.MD_UPLOAD_MORE.getRetCode(), ResponseConstants.MD_UPLOAD_MORE.getRetMsg());
					return resp;
				}
			}
			//通过借贷申请id获取借贷申请信息
			LoanProductApply loanProductApply= loanProductApplyService.selectLoanProductApplyById(loanProductApplyId);
			req.setLoanProductId(loanProductApply.getLoanProductId());
				req.setOrgId(orgId);
					if(StringUtils.isBlank(req.getShare())) {
						req.setShare("1"); //设置默认 共享
					}
					
					ImgAndDocTitle imgAndDocTitle2 = imgAndDocModelService.getImgAndDocTitleById(req.getImgClassId());
					
					if(imgAndDocTitle2 != null) {
						ImgAndDocModel imgAndDocModel = imgAndDocModelService.getImgAndDocModelById(imgAndDocTitle2.getImgDocId());
						
						if(imgAndDocModel != null) {
							Calendar calendar = Calendar.getInstance();
							
							int year = calendar.get(Calendar.YEAR);
							
							int month = calendar.get(Calendar.MONTH);
							
							int day = calendar.get(Calendar.DAY_OF_YEAR);
							//在系统中文件名称
							String tempName = null;
							
							//String fileName = fileIo.getOriginalFilename();
							String fileName = req.getName();
							//生成文件上传路径
							StringBuilder path = new StringBuilder();
							//文件上传基本路径
							StringBuilder fileBasePath = new StringBuilder(this.rootPath + this.basePath); 
							
							path.append("/").append(imgAndDocModel.getCode());
							path.append("/").append(imgAndDocTitle2.getCode());
							path.append("/").append(year);
							path.append("/").append(month);
							path.append("/").append(day);
							
							synchronized(this) {
								tempName = String.valueOf(number);
								number++;
							}
							
							//获得文件扩展名
							String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
							tempName += ".";
							tempName += extension;
							
							File targetFile = new File(fileBasePath.append(path).toString());  
							
							if(!targetFile.exists()) {  
								targetFile.mkdirs();  
							}
							
							targetFile = new File(fileBasePath.toString(), tempName);
							
							if(!targetFile.exists()) {
								targetFile.createNewFile();
							}
							
							System.out.println("upload:" + targetFile.getPath());
							
							path.append("/").append(tempName);
							// 图片上传成功标记
							boolean flag = false;
							BufferedInputStream bin = null;
							BufferedOutputStream bos = null;
							
							try {
								bos = new BufferedOutputStream(new FileOutputStream(targetFile));
								bin = new BufferedInputStream(file.getInputStream());
								
								byte[] buffer = new byte[8 * 1024];
								int read = 0;
								while ((read = bin.read(buffer)) != -1) {
									bos.write(buffer, 0, read);
								}
								bos.flush();
								flag = true;
							} catch (Exception e) {
								resp.setExceptionFlag(ResponseConstants.REQUEST_FAIL.getRetCode());
								resp.setResponseCode(ResponseConstants.MD_UPLOAD_NONE.getRetCode());
								resp.setResponseMessage(ResponseConstants.MD_UPLOAD_NONE.getRetMsg());
								e.printStackTrace();
							} finally {
								if(bos != null) bos.close();
							}
							
							if (flag) {
								//fileIo.transferTo(targetFile);
								//获得影像记录ID
								String dataId2 = imgAndDocModelService.getMaterialDataId(req.getLoanProductApplyId());
								
								if(dataId2 == null) {
									dataId2 = imgAndDocModelService.saveMaterialData(req.getId(), imgAndDocTitle2.getImgDocId(), req.getLoanProductApplyId(), extension, fileName, imgAndDocModel.getCode(), imgAndDocTitle2.getCode(), path.toString(), orgId, req.getShare());
									if(dataId2 != null) {
										//保存业务与模型定义数据关联
										LoanProductApplyModelV loanProductApplyModelV = new LoanProductApplyModelV();
										loanProductApplyModelV.setLoanProductApplyId(req.getLoanProductApplyId());
										loanProductApplyModelV.setModelType("IMM");
										loanProductApplyModelV.setModelCode(imgAndDocModel.getCode());
										loanProductApplyModelV.setModelVersion(imgAndDocModel.getVersion());
										loanProductApplyModelV.setModelInstanceId(dataId2);
										loanProductApplyModelService.saveLoanProductApplyModelV(loanProductApplyModelV);
									}
								} else {
									imgAndDocModelService.addMaterialData(req.getId(), dataId2, extension, fileName, imgAndDocModel.getCode(), imgAndDocTitle2.getCode(), path.toString(), orgId, req.getShare());
								}
								
								resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
								resp.setResponseCode(ResponseConstants.MD_UPLOAD_SUCCESS.getRetCode());
								resp.setResponseMessage(ResponseConstants.MD_UPLOAD_SUCCESS.getRetMsg());
							}
							
						} else {
							resp.setExceptionFlag(ResponseConstants.REQUEST_FAIL.getRetCode());
							resp.setResponseCode(ResponseConstants.MD_UPLOAD_CFGNOTFOUND.getRetCode());
							resp.setResponseMessage(ResponseConstants.MD_UPLOAD_CFGNOTFOUND.getRetMsg());
						}
					} else {
						resp.setExceptionFlag(ResponseConstants.REQUEST_FAIL.getRetCode());
						resp.setResponseCode(ResponseConstants.MD_UPLOAD_CFGNOTFOUND.getRetCode());
						resp.setResponseMessage(ResponseConstants.MD_UPLOAD_CFGNOTFOUND.getRetMsg());
					}

		return resp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**  
	* @Title: deleteLoanMaterial
	* @Description: TODO(影像资料删除) 
	* @param req 接收app端传入参数 
	* @return BaseHttpParamsResp 返回给app端响应
	* @throws Exception
	*/  
	@RequestMapping(path = "/deleteLoanMaterial", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @RequestAccessAnnotation(modelName = "影像资料删除", modelType = RequestAccessConstants.SEARCH, desc = "删除影像资料方法")
	public BaseHttpParamsResp deleteLoanMaterial(String loanProductApplyId, String id, String classId) throws Exception {
		logger.info("影像资料删除");
		BaseHttpParamsResp resp = new BaseHttpParamsResp();
		Map<String, Object> fileInfo = imgAndDocModelService.getFileInfoByModelInfo(loanProductApplyId, classId, id);
		if(fileInfo != null) {
			String fileId = (String) fileInfo.get("id");
			int i = imgAndDocModelService.deleteFileInfo(fileId);
			if(i > 0) {
				String filePath = (String) fileInfo.get("filePath");
				if(StringUtils.isNoneBlank(filePath)) {
					String fullPath = this.rootPath + basePath + filePath;
					File file = new File(fullPath);
					if(file.delete()) {
						loanProductApplyService.modifyUpdateTime(loanProductApplyId);
						resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
						resp.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
						resp.setResponseMessage(ResponseConstants.SUCCESS.getRetMsg());
					} else {
						resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
						resp.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
						resp.setResponseMessage(ResponseConstants.SUCCESS.getRetMsg());
					}
				} else {
					resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
					resp.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
					resp.setResponseMessage(ResponseConstants.SUCCESS.getRetMsg());
				}
			} else {
				resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
				resp.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
				resp.setResponseMessage(ResponseConstants.SUCCESS.getRetMsg());
			}
		} else {
			resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
			resp.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
			resp.setResponseMessage(ResponseConstants.SUCCESS.getRetMsg());
		}
		
		//resp.createSign();
		
		return resp;
	}
	
	@RequestMapping(path = "/android_getImageInfoList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @RequestAccessAnnotation(modelName="影像资料数据下载",modelType=RequestAccessConstants.SEARCH,desc="获取影像资料图片基本信息")
    public Map<String,Object> android_getImageInfoList(String loanProductApplyId){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			
			// 影像资料数据信息
			List<ImageDataInfo> imageInfoList = imgAndDocModelService.getImageInfoList(loanProductApplyId);
			//通过借贷申请id获取当前模型分类信息
			List<ImgAndDocTitle> imgAndDocTitle = imgAndDocModelService.getImgAndDocTitleInfo(loanProductApplyId);
			String dataId = imgAndDocModelService.getMaterialDataIdPro(imgAndDocTitle.get(0).getImgDocId().toString(), loanProductApplyId);
			if(imgAndDocTitle != null&&imgAndDocTitle.size()>0){
				List<Map<String,Object>> classData = new ArrayList<Map<String,Object>>();
				for(ImgAndDocTitle title:imgAndDocTitle){
					int imageClassNum =  imgAndDocModelService.imageClassNum(title.getId().toString(),dataId);
					Map<String,Object> classMap =  new HashMap<>();
					classMap.put("productApplyId",loanProductApplyId);
					classMap.put("modelId",title.getImgDocId());
					classMap.put("classId",title.getId());
					classMap.put("className",title.getName());
					classMap.put("max", imageClassNum);
					classMap.put("min", title.getFloor());
					List<Map<String,Object>> imageData = new ArrayList<Map<String,Object>>();
					for(ImageDataInfo info:imageInfoList){
						Map<String,Object> imageDataMap =  new HashMap<>();
						imageDataMap.put("productApplyId",loanProductApplyId);
						imageDataMap.put("modelId",title.getImgDocId());
						imageDataMap.put("classId",title.getId());
						imageDataMap.put("id",info.getId());
						imageDataMap.put("src",info.getUrl());
						imageDataMap.put("flag","true");
						if(title.getId().toString().equals(info.getClassId())){
							imageData.add(imageDataMap);
						}
					}
					classMap.put("imageData", imageData);
					classData.add(classMap);
				}
				result.put("status", "0000");
				result.put("message", "成功");
				result.put("classData", classData);
				
			}else{
				result.put("status", "0001");
				result.put("message", "成功");
				result.put("data", "");
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("status", "0002");
			result.put("message", "成功");
		}

		logger.info("影像资料数据下载");
		return result;
    }
	
	@RequestMapping(path = "/ios_getImageInfoList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @RequestAccessAnnotation(modelName="影像资料数据下载",modelType=RequestAccessConstants.SEARCH,desc="获取影像资料图片基本信息")
    public BaseHttpParamsResp ios_getImageInfoList(String loanProductApplyId, String openId)
                    throws Exception {
		BaseHttpParamsResp baseHttpParamsResp = new BaseHttpParamsResp();
		
		Map<String,Object> mp =  new HashMap<>();
		List<Map<String,Object>> classList = new ArrayList<Map<String,Object>>();
		// 影像资料数据信息
		List<ImageDataInfo> imageInfoList = imgAndDocModelService.getImageInfoList(loanProductApplyId);
		//通过借贷申请id获取当前模型分类信息
		List<ImgAndDocTitle> imgAndDocTitle = imgAndDocModelService.getImgAndDocTitleInfo(loanProductApplyId);
		if(imgAndDocTitle != null&&imgAndDocTitle.size()>0){
			mp.put("modelId",imgAndDocTitle.get(0).getImgDocId());
			for(ImgAndDocTitle title:imgAndDocTitle){
				Map<String,Object> item =  new HashMap<>();
				item.put("classId",title.getId());
				item.put("className",title.getName());
				List<ImageDataInfo> list = new ArrayList<ImageDataInfo>();
				for(ImageDataInfo info:imageInfoList){
					if(title.getId().equals(info.getClassId())){
						info.setUrl(info.getUrl()+"&openId="+openId);
						list.add(info);
					}
				}
				item.put("data", list);
				classList.add(item);
			}
		}
		mp.put("data", classList);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),ResponseConstants.SUCCESS.getRetMsg(), mp);
		//baseHttpParamsResp.createSign();
		logger.info("影像资料数据下载");
		
        return baseHttpParamsResp;
    }
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(path = "/uploadFileCheck", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @RequestAccessAnnotation(modelName="影像资料校验规则",modelType=RequestAccessConstants.SEARCH,desc="获取影像分类数量信息")
    public BaseHttpParamsResp uploadFileCheck(String loanProductApplyId)
                    throws Exception {
		BaseHttpParamsResp baseHttpParamsResp = new BaseHttpParamsResp();
		//通过借贷申请id获取当前模型分类信息
		List<ImgAndDocTitle> imgAndDocTitle = imgAndDocModelService.getImgAndDocTitleInfo(loanProductApplyId);
		String dataId = imgAndDocModelService.getMaterialDataIdPro(imgAndDocTitle.get(0).getImgDocId().toString(), loanProductApplyId);
		for (ImgAndDocTitle imgAndDocTitle2 : imgAndDocTitle) {
			//获得文件可上传数量
			int imageClassNum =  imgAndDocModelService.imageClassNum(imgAndDocTitle2.getId().toString(),dataId);
			imgAndDocTitle2.setSize(imageClassNum);
			/*//获取文件最少数量
			int imageFloorNum = imgAndDocTitle2.getFloor();
			//获取现有针对于影像分类上传的文件的数量
			// 影像资料数据信息
			List<ImageDataInfo> imageInfoList = imgAndDocModelService.getImageInfoList(loanProductApplyId);
			int imageDataNum = 0;
			for (ImageDataInfo imageDataInfo : imageInfoList) {
				if(imageDataInfo.getClassId().equals(imgAndDocTitle2.getId().toString())){
					imageDataNum ++;
				}
			}
			if(imageFloorNum > imageDataNum){
				baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.MD_UPLOAD_LESS.getRetCode(), ResponseConstants.MD_UPLOAD_LESS.getRetMsg());
				return baseHttpParamsResp;
			}
			if(imageClassNum > imageDataNum){
				continue;
			}else{
				baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.MD_UPLOAD_MORE.getRetCode(), ResponseConstants.MD_UPLOAD_MORE.getRetMsg());
				return baseHttpParamsResp;
			}*/
		}
			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
					ResponseConstants.SUCCESS.getRetMsg(),imgAndDocTitle);
		//baseHttpParamsResp.createSign();
		logger.info("影像资料数据校验");
		
        return baseHttpParamsResp;
    }
}
