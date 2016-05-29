/**
 * 
 */
package bl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import models.Ticket;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class JSONExporter implements Exporter {

	/**
	 * constructor
	 */
	public JSONExporter() {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean export(Integer showId) {
		boolean ok = false;
		TicketManager tm = new TicketManager();
		JSONObject jsonObj = new JSONObject();
		JSONArray tempArray = new JSONArray();
		tempArray.add("nr");
		tempArray.add("row");
		jsonObj.put("id", tempArray);
		
		List<Ticket> tickets = tm.getAllTicketsByShowId(showId);
		for (Ticket t : tickets)
		{
			JSONArray jsonArray = new JSONArray();
			jsonArray.add(t.getNr());
			jsonArray.add(t.getRow());
			jsonObj.put(t.getId(), jsonArray);
		}
		
		try {

			FileWriter file = new FileWriter("show.json");
			file.write(jsonObj.toJSONString());
			file.flush();
			file.close();
			
			ok = true;
			
		} catch (IOException e) {
			
		}
		
		return ok;
	}

}
