package uk.hotten.tkmpuller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Puller {

    private static String[] categories = {
            // womens
            "01020000", // clothing (incl nightwear, lingerie, mb, gl, most sport)
            "01030000", // active clothing
            "01040000", // accessories
            "01040600", // jewelery
            "01050000", // shoes
            "01050800", // gl shoes
            "01060000", // beauty
            "05010000", // clearance

            // mens
            "02020000", // clothing
            "02040200", // active clothing
            "02030000", // accessories
            "02050000", // shoes
            "02030900", // grooming
            "05020000", // clearance

            // kids and toys
            "03020000", // baby and nursery
            "03030000", // girls
            "03040000", // boys
            "03050000", // toys and books
            "03060000", // shoes
            "05030000", // clearance

            // home
            "04020150", // bedding
            "04050200", // bathroom
            "04030000", // kitchen
            "04040900", // furniture
            "04120700", // furnishings and accessories
            "04100500", // stationary and books
            "04050800", // pets
            "04100200", // luggage
            "05040000", // clearance
    };

    public static void main(String[] args) throws InterruptedException {
        // clear codes
        System.out.println("TKM Puller v1.0");
        System.out.println(" ");
        System.out.println("Clearing current code finder price files...");
        try {
            File dir = new File("/volume1/tkm/");
            for (File f : dir.listFiles()) {
                if (f.getName().startsWith("cf-")) {
                    f.delete();
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to clear code finder price files: " + e.getMessage());
        }

        System.out.println("Starting category pull-down...");
        for (String s : categories) {
            processCategory(s);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static void processCategory(String category) {
        System.out.println("Processing category " + category);
        int amountProcessed = 0;
        int max = Integer.MAX_VALUE;
        try {
            while (amountProcessed < max) {
                URL url = new URL("http://core.dxpapi.com/api/v1/core/?_br_uid_2=uid%3D7797686432023%3Av%3D11.5%3Ats%3D1428617911187%3Ahc%3D55&account_id=7256&domain_key=tkmaxx&ref_url=https%3A%2F%2Ftkmaxx.com&request_type=search&search_type=keyword&facet.range=price&fl=pid%2Ctitle%2Cprice%2Cdescription%2Curl%2Cbrand%2Cthumb_image%2Ccode%2Cmaterial%2Ccolor%2Ccolor_group%2Crrp%2Cfmt_rrp%2Csave_price%2Cfmt_save_price%2Cstyle_name%2Cwas_price%2Cfmt_was_price%2Cpercent_saving%2Cstock%2Cstock_status%2Cis_low_stock%2Cmat_badge_name%2Cmat_badge_image%2Cmat_badge_aa_text%2Cbadge_name%2Cbadge_image%2Cbadge_aa_text%2Cfmt_unit_price%2Cprimary_category_name%2Cis_shy_brand%2Cbundle_price%2Cbundle_rrp%2Cbundle_was_price%2Cdepartment%2Cis_bundle%2Cfmt_price%2Cskuid%2Cprimary_category_code%2Cprimary_category_path%2Cpublished_date%2Cpublished_days%2Cmh_dept_name%2Cmh_dept%2Cmh_class%2Cmh_class_name%2Cbundle_name%2Cbundle_skuid%2Cenvironment&rows=200&start=" + amountProcessed + "&stats.field=price%2CMARGIN_DOLLAR&url=https%3A%2F%2Fp1-tjx.tkmaxx.com&view_id=tkmaxx-uk&q=" + category);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                //Getting the response code
                int responsecode = conn.getResponseCode();
                if (responsecode != 200) {
                    throw new RuntimeException("HttpResponseCode: " + responsecode);
                } else {

                    String inline = "";
                    Scanner scanner = new Scanner(url.openStream());

                    //Write all the JSON data into a string using a scanner
                    while (scanner.hasNext()) {
                        inline += scanner.nextLine();
                    }

                    //Close the scanner
                    scanner.close();

                    ObjectMapper objectMapper = new ObjectMapper();
                    Data.ResponseWrapper responseWrapper = objectMapper.readValue(inline, Data.ResponseWrapper.class);
                    System.out.println("Found " + responseWrapper.response.numFound);
                    max = responseWrapper.response.numFound;

                    for (Data.Doc d : responseWrapper.response.docs) {
                        File file = new File("/volume1/tkm/" + d.pid + ".json");
                        objectMapper.writeValue(file, d);

                        // Code finder
                        String cfPrice = Double.toString(d.price);
                        cfPrice = cfPrice.replace(".", "");
                        File cfFile = new File("/volume1/tkm/cf-" + d.mhDept + "-" + cfPrice + ".txt");
                        if (!cfFile.exists() && !cfFile.isDirectory()) {
                            objectMapper.writeValue(cfFile, d.code.substring(0, 2) + " / 10 / " + d.code.substring(2, 8) + " / " + d.fmtPrice);
                        }

                        amountProcessed++;
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }
}
