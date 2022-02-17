package ad.sprbt1.service;

import ad.sprbt1.model.CurrentData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class CrvrsService {
    private List<CurrentData> currentDataList = new ArrayList<>();

    public List<CurrentData> getCurrentDataList() {
        return currentDataList;
    }
    private static String dataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
    @PostConstruct
    @Scheduled(cron="* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {
        List<CurrentData> newCurrentData = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(dataUrl)).build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());

        StringReader in = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
//            String country_region = record.get("Combined_Key");
//            System.out.println(country_region);
//            String date = record.get("2/14/22");
//            System.out.println(date);
            CurrentData currentData = new CurrentData();
            currentData.setLocation(record.get("Combined_Key"));
            currentData.setConfirmedCases(Integer.parseInt(record.get(record.size()-1)));
            newCurrentData.add(currentData);
            this.currentDataList = newCurrentData;
        }

    }
}
