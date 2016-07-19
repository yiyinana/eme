package cn.xidian.web.service;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import cn.xidian.entity.ClazzCoursePoint;
import cn.xidian.entity.TeachingTarget;
import cn.xidian.entity.TeachingTargetEvaluate;
import cn.xidian.web.bean.B1;
import cn.xidian.web.bean.B2;

public class CourseTargetDetailService {

	public List<B1> getB1(List<TeachingTarget> targets,List<TeachingTargetEvaluate> targetValues){
		DecimalFormat df = new DecimalFormat("#0.000");// 用于格式化Double类型数据
		List<B1> claCursB1s = new LinkedList<B1>();
		
		if (targetValues.size() == 0) {
		}
		
		for(int i=0;i<targets.size();i++){
			TeachingTarget target = targets.get(i);
			TeachingTargetEvaluate targetValue = targetValues.get(i);
			B1 bValue1 = new B1();
			bValue1.setTarget("目标"+(i+1));
			bValue1.setTchtargetClassTargetValue(target.getTchtargetClassTargetValue().toString());
			bValue1.setTchtargetClassEvaValue(df.format(targetValue.getTchtargetClassEvaValue()));
			bValue1.setTchtargetHomeworkTargetValue(target.getTchtargetHomeworkTargetValue().toString());
			bValue1.setTchtargetWorkEvaValue(df.format(targetValue.getTchtargetWorkEvaValue()));
			bValue1.setTchtargetExpTargetValue(target.getTchtargetExpTargetValue().toString());
			bValue1.setTchtargetExpEvaValue(df.format(targetValue.getTchtargetExpEvaValue()));
			bValue1.setTchtargetMidTargetValue(target.getTchtargetMidTargetValue().toString());
			bValue1.setTchtargetMidEvaValue(df.format(targetValue.getTchtargetMidEvaValue()));
			bValue1.setTchtargetFinTargetValue(target.getTchtargetFinTargetValue().toString());
			bValue1.setTchtargetFinEvaValue(df.format(targetValue.getTchtargetFinEvaValue()));
			bValue1.setA1(df.format(targetValue.getA1()));
			bValue1.setB1(df.format(targetValue.getB1()));
			claCursB1s.add(bValue1);
		}
		return claCursB1s;
	}
	
	public List<B2> getB2(List<ClazzCoursePoint> ccPoints){
		DecimalFormat df = new DecimalFormat("#0.000");// 用于格式化Double类型数据
		List<B2> claCursB2s = new LinkedList<B2>();
		for(int i=0;i<ccPoints.size();i++){
			ClazzCoursePoint ccPoint = ccPoints.get(i);
			B2 bValue2 = new B2();
			bValue2.setPoint(ccPoint.getIndPoint().getIndPointNum()+ccPoint.getIndPoint().getIndPointContent());
			bValue2.setTargetTarValue(df.format(ccPoint.getTargetTarValue()));
			bValue2.setA2(df.format(ccPoint.getA2()));
			bValue2.setB2(df.format(ccPoint.getB2()));
			claCursB2s.add(bValue2);
		}
		return claCursB2s;
	}
}
