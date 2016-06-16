public class KayitServisi extends FirebaseInstanceIdService {

    public void onTokenRefresh() {
        String clientKey = "";

        FirebaseInstanceId fid = FirebaseInstanceId.getInstance();
        clientKey = fid.getToken();

        Log.e("onRefreshToken", "GCM Kaydı Yapıldı. clientKey : " + clientKey);

        try {
            String adr = "http://192.168.0.2:8080/DatabaseService/inventory/registry/todb";
            URL url = new URL(adr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            BufferedWriter outwrite = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            outwrite.append("{ "
                    + "\"reg_id\" : \"" + clientKey + "\""
                    + " }");
            outwrite.flush();
            StringBuilder build;
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            build = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null) {
                build.append(str).append("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}