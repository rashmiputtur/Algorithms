package gitStatistics;

import com.google.cloud.Timestamp;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryResponse;
import com.google.cloud.bigquery.QueryResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Class to fetch commit messages from BigQuery 
 */
public class GetDataFromBigQuery {
	static CollectStatistics collectStats = new CollectStatistics();
	
	public static void main(String args[]) throws Exception{
		executeQuery();
	}

	public static void executeQuery() throws Exception {
		String currentMsgTimestamp = null;
	
		//Class in turn calls the GetMedian and GetMean classes
		GetCommitMessages commitMsgObj = new GetCommitMessages();
		
		// Create a JOB ID for a unique identifier
		JobId jobID = JobId.of(UUID.randomUUID().toString());
		
		BigQuery bigQuery = BigQueryOptions.getDefaultInstance().getService();
		QueryJobConfiguration queryConfig = QueryJobConfiguration
				.newBuilder(
						"SELECT payload, EXTRACT(DATETIME FROM created_at) "
						+ "FROM `githubarchive.day.20170618` "
						+ "WHERE Type = 'PushEvent' "
						+ "LIMIT 75;").setUseLegacySql(false).build();


		Job queryJob = bigQuery.create(JobInfo.newBuilder(queryConfig)
				.setJobId(jobID).build());

		// Wait for the query complete
		queryJob = queryJob.waitFor();

		// Check for errors
		if (queryJob == null) {
			throw new RuntimeException("Job no longer exists");
		} else if (queryJob.getStatus().getError() != null) {
			throw new RuntimeException(queryJob.getStatus().getError()
					.toString());
		}

		// Get the results
		QueryResponse queryResponse = bigQuery.getQueryResults(jobID);
		QueryResult queryResult = queryResponse.getResult();
		
		

		//Iterate over each commit message
		while (queryResult != null) {
			for (List<FieldValue> row : queryResult.iterateAll()) {
				String payload = row.get(0).getStringValue();
				
				//Parse the output JSON to obtain the commit message
				JSONObject jObject  = new JSONObject(payload); 				
				JSONArray commitArray = jObject.getJSONArray("commits");
				System.out.println(commitArray);
				JSONObject commitObject = (JSONObject) commitArray.get(0);
				
				//get the commits field
				String commitMessage = commitObject.getString("message");
				commitMsgObj.getMessage(commitMessage);
				
				//Get the created_at field to keep track of last fetched record
				currentMsgTimestamp =  (String) row.get(1).getValue();
//				String dtae = dateFormatUTC.format(new Date(Double.valueOf(t).longValue() ));
//				System.out.println(dtae);
				
			}
			queryResult = queryResult.getNextPage();
		} // End of while
		
		collectStats.getLastRecordTimeStamp(currentMsgTimestamp);
	}
}
