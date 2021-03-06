package eventcenter.leveldb.tx.listeners;

import eventcenter.DateUtils;
import eventcenter.api.CommonEventSource;
import eventcenter.api.EventListener;
import eventcenter.api.EventSourceBase;
import eventcenter.api.annotation.ListenerBind;
import eventcenter.leveldb.tx.EventIdContext;
import org.springframework.stereotype.Component;

/**
 * 监听example.manual事件
 * @author JackyLIU
 *
 */
@Component	// 如果使用Spring，需要设置spring的context-scan，并将这个包配置到扫描包中
@ListenerBind("example.annotation") // 设置监控的事件名称
public class AnnotationEventListener implements EventListener {

	@Override
	public void onObserved(CommonEventSource source) {
		CommonEventSource evt = source;	// 默认的事件源使用的是CommonEventSource，当然EventSourceBase是可以定制的，请参考文档，建议使用默认方式
		String data1 = evt.getArg(0, String.class); // 获取事件参数，下标从0开始
		Integer data2 = evt.getArg(1, Integer.class);	

		try {
			Thread.sleep(data2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(DateUtils.getNowDate() + " 消费了事件,数量： " + EventIdContext.increaseAndGet());
	}

}
