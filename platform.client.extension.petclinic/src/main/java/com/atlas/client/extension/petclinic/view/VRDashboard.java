package com.atlas.client.extension.petclinic.view;

import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button.Style;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Paragraph;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile.Size;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.atlas.client.extension.petclinic.view.demos.VPChartDemo;
import com.atlas.client.extension.petclinic.view.demos.VPTreegridDemo;
import com.atlas.client.extension.petclinic.view.home.VPNotes;
import com.atlas.client.extension.petclinic.view.owner.VisitLineItem;
import com.atlas.client.extension.petclinic.view.pet.VPAllPets;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Sandeep Mantha
 *
 */

@Domain(value = "petclinicdashboard", includeListeners = { ListenerType.websocket })
@Repo(value = Repo.Database.rep_none, cache = Repo.Cache.rep_device)
@Getter @Setter
@ViewRoot(layout = "home")
public class VRDashboard {

	@Label("Home")
	@Page(defaultPage = true)
	private VPDashboard vpDashboard;
	
	@Label("Notes")
	@Page
	private VPNotes vpNotes;
	
	@Label("TreeGrid Demo")
	@Page
	private VPTreegridDemo vpTreegridDemo;
	
	@Label("Charts Demo")
	@Page
	private VPChartDemo vpChartDemo;
	
	@Label("Pets")
	@Page
	private VPAllPets vpAllPets;

	@Model
	@Getter @Setter
	public static class VPDashboard  {

		@Tile(imgSrc="images/pets.png", size=Size.Large)
        private VTDashboard vtDashboard;
		
    }
	
	@Model
	@Getter @Setter
	public static class VTDashboard  {

		@Label("Hello!! Welcome to Pet Clinic. This application is the reference implementation for Nimbus Framework.")
    	@Paragraph(cssClass="font-weight-bold")
    	private String headerCallSection;
		
		//@Section
		//private VSMyVisits vsMyVisits;
		
    }
	
//	@Model
//	@Getter @Setter
//	public static class VSMyVisits  {
//		
//		@Label("Owners")
//		@Button(style = Style.SECONDARY)
//		@Config(url = "/p/ownerlandingview/_new")
//		private String goToOwners;
//		
//		@Label("My Visits")
//		@MapsTo.Path(linked = false)       
//		@Grid(onLoad = true, pageSize = "7")
//		@Config(url = "/vpDashboard/vtMyVisits/vsMyVisits/myVisits.m/_process?fn=_set&url=/p/visit/_search?fn=example")
//		private List<VisitLineItem> myVisits;
//		
//    }
	
}
