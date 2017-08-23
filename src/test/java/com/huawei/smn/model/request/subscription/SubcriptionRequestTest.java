package com.huawei.smn.model.request.subscription;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.model.AuthenticationBean;

public class SubcriptionRequestTest {
    private static Logger logger = LoggerFactory.getLogger(SubcriptionRequestTest.class);
    SubcriptionRequest subcriptionRequest = new SubcriptionRequest();
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";
    final static String REGION_ID = "cn-north-1";
    final static String SMN_ENDPOINT = "https://smn.cn-north-1.myhwclouds.com";

    @Before
    public void setUp() {
        SmnConfiguration smnConfiguration = mock(SmnConfiguration.class);
        authenticationBean = mock(AuthenticationBean.class);
        when(authenticationBean.getProjectId()).thenReturn(PROJECT_ID);
        when(smnConfiguration.getRegionId()).thenReturn(REGION_ID);
        when(smnConfiguration.getSmnEndpoint()).thenReturn(SMN_ENDPOINT);

        //调试bug
        subcriptionRequest.setProjectId(PROJECT_ID);
        subcriptionRequest.setSmnEndpoint(SMN_ENDPOINT);


    }

    @Test
    public void testGetRequestUrl() throws Exception {
        logger.info("subcriptionRequest getRequestUrl");
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        subcriptionRequest.setTopicUrn(topicUrn);
        subcriptionRequest.setProtocol("sms");
        String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi/subscriptions";
        System.out.println("---------------");
        System.out.println(subcriptionRequest.getRequestUri());
       Assert.assertEquals(requestURL, SMN_ENDPOINT + subcriptionRequest.getRequestUri());
    }

    @Test(expected = RuntimeException.class)
    public void testGetRequestUrl1() throws Exception {
        logger.info("subcriptionRequest getRequestUrl");
        when(subcriptionRequest.getRequestUri()).thenCallRealMethod();
        String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi/subscriptions";
        Assert.assertEquals(requestURL, SMN_ENDPOINT  + subcriptionRequest.getRequestUri());
    }

    @Test(expected = RuntimeException.class)
    public void testGetRequestParameterMap() throws Exception {

        when(subcriptionRequest.getRequestParameterMap()).thenCallRealMethod();
        Assert.assertNull(subcriptionRequest.getRequestParameterMap().get("protocol"));
        Assert.assertNull(subcriptionRequest.getRequestParameterMap().get("endpoint"));
        when(subcriptionRequest.getProtocol()).thenReturn("sms");
        when(subcriptionRequest.getEndpoint()).thenReturn("18388432306");
        Assert.assertNotNull(subcriptionRequest.getRequestParameterMap().get("protocol"));
        Assert.assertNotNull(subcriptionRequest.getRequestParameterMap().get("endpoint"));
    }

    @Test
    public void testGetRequestParameterMap2() throws Exception {
        //when(subcriptionRequest.getRequestParameterMap()).thenCallRealMethod();
        //when(subcriptionRequest.getProtocol()).thenReturn("sms");
        //when(subcriptionRequest.getEndpoint()).thenReturn("18388432306");
        subcriptionRequest.setProtocol("sms");
        subcriptionRequest.setEndpoint("18388432306");
        Assert.assertNotNull(subcriptionRequest.getRequestParameterMap().get("protocol"));
        Assert.assertNotNull(subcriptionRequest.getRequestParameterMap().get("endpoint"));
    }

}
