package com.pkrete.xrd4j.rest.util;

import java.util.Map;
import java.util.TreeMap;
import junit.framework.TestCase;

/**
 * Test cases for JSONToXMLConverter class.
 *
 * @author Petteri Kivimäki
 */
public class ClientUtilTest extends TestCase {

    /**
     * No parameters, no resource id, no slash in base URI, no question mark
     * in base uri.
     */
    public void testBuildClientURL1() {
        String baseURL = "http://api.test.com";
        String correctURL = baseURL;
        Map<String, String> params = new TreeMap<String, String>();
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * No parameters (null), no resource id, no slash in base URI, no question
     * mark in base uri.
     */
    public void testBuildClientURL2() {
        String baseURL = "http://api.test.com";
        String correctURL = baseURL;
        Map<String, String> params = null;
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * Three parameters, no resource id, no slash in base URI, no question mark
     * in base uri.
     */
    public void testBuildClientURL3() {
        String baseURL = "http://api.test.com";
        String correctURL = baseURL + "?key1=value1&key2=value2&key3=value3";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("key1", "\nvalue1    ");
        params.put("key2", "value2\r");
        params.put("key3", "\r\nvalue3\r\n");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * Three parameters, no resource id, slash in base URI, no question mark
     * in base uri.
     */
    public void testBuildClientURL4() {
        String baseURL = "http://api.test.com/";
        String correctURL = baseURL + "?key1=value1&key2=value2&key3=value3";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        params.put("key3", "value3");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * Three parameters, no resource id, no slash in base URI, question mark
     * in base uri.
     */
    public void testBuildClientURL5() {
        String baseURL = "http://api.test.com?";
        String correctURL = baseURL + "key1=value1&key2=value2&key3=value3";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("key1", "    value1");
        params.put("key2", "value2");
        params.put("key3", "    value3  ");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * Three parameters, no resource id, slash in base URI, question mark
     * in base uri.
     */
    public void testBuildClientURL6() {
        String baseURL = "http://api.test.com/?";
        String correctURL = baseURL + "key1=value1&key2=value2&key3=value3";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        params.put("key3", "value3");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * No parameters, resource id, no slash in base URI.
     */
    public void testBuildClientURL7() {
        String baseURL = "http://api.test.com";
        String correctURL = baseURL + "/10";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("resourceId", " \n10\n");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * No parameters, resource id, slash in base URI.
     */
    public void testBuildClientURL8() {
        String baseURL = "http://api.test.com/";
        String correctURL = baseURL + "10";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("resourceId", "\r10\r    ");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * Two parameters, resource id, no slash in base URI.
     */
    public void testBuildClientURL9() {
        String baseURL = "http://api.test.com";
        String correctURL = baseURL + "/10?key2=value2&key3=value3";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("resourceId", "\r\n10\r\n");
        params.put("key2", " value2 ");
        params.put("key3", "value3");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * Two parameters, resource id, slash in base URI.
     */
    public void testBuildClientURL10() {
        String baseURL = "http://api.test.com/";
        String correctURL = baseURL + "10?key2=value2&key3=value3";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("resourceId", "  10 ");
        params.put("key2", "value2");
        params.put("key3", "value3");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * One parameter in base URI, two parameters, no resource id, no
     * slash in base URI.
     */
    public void testBuildClientURL11() {
        String baseURL = "http://api.test.com?param=1";
        String correctURL = baseURL + "&key2=value2&key3=value3";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("key2", "  value2");
        params.put("key3", "value3  ");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }

    /**
     * One parameter in base URI, two parameters, no resource id,
     * slash in base URI.
     */
    public void testBuildClientURL12() {
        String baseURL = "http://api.test.com/?param=1";
        String correctURL = baseURL + "&key2=value2&key3=value3";
        Map<String, String> params = new TreeMap<String, String>();
        params.put("key2", "value2");
        params.put("key3", "value3");
        String resultURL = ClientUtil.buildTargetURL(baseURL, params);
        assertEquals(correctURL, resultURL);
    }
}
