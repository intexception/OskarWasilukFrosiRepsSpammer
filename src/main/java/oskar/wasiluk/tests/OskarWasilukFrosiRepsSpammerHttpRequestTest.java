package oskar.wasiluk.tests;


import oskar.wasiluk.util.OskarWasilukFrosiRepsSpammerHttpRequestUtility;

public class OskarWasilukFrosiRepsSpammerHttpRequestTest {

    public void testSendPostRequest() {
        String url = "http://httpbin.org/post";
        String payload = "{ \"test\": \"data\" }";

        try {
            String response = OskarWasilukFrosiRepsSpammerHttpRequestUtility.sendPostRequest(url, payload);
        } catch (Exception e) {
            System.out.println("Request failed: " + e.getMessage());
        }
    }
}