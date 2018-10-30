package activiti;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.platform.BaseFrameBootApplication;
import com.company.platform.base.service.activiti.WeiXinActivitiService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes =BaseFrameBootApplication.class )
public class ActivitiTestCase2 {
	@Autowired
	private WeiXinActivitiService weiXinActivitiService;
	
	
	
//	/**
//	 * 获取button
//	 */
//	
//	public void  getButton(){
//		List<FunctionInfo> josn = weiXinActivitiService.getButton("66a3bf71-d791-462a-b3ed-a1e76d106ec1", "rbProcess");
//		System.out.println(JSONArray.toJSONString(josn));
//	}
//	
	/**
	 * 开启流程
	 */
	@Test
	public void  startActiviti(){
		Map<String,String> josn = weiXinActivitiService.startActiviti("8c5b3166-fcc5-44fb-a574-b26d589c6206", "tasktwo", "submit", "b6b9dd62-6e34-4196-8e16-1a35d0673e56", "朝阳业务人员2");
		System.out.println(JSONObject.toJSONString(josn));
	}
	
	/**
	 * 流程审批
	 */

	public void  approveActiviti(){
		Map<String, String> josn = weiXinActivitiService.approveActiviti("b6b9dd62-6e34-4196-8e16-1a35d0673e56","852ef3d7-13c1-41cb-8051-976a2a1671e6", "测试啊啊啊啊啊", "submit", "{'pass':'y'}", "submit");
		System.out.println(JSONArray.toJSONString(josn));
	}
}
