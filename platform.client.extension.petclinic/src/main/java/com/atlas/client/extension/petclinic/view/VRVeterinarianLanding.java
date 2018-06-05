package com.atlas.client.extension.petclinic.view;

import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.atlas.client.extension.petclinic.core.Veterinarian;
import com.atlas.client.extension.petclinic.core.VeterinarianLineItem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author Sandeep Mantha
 * @author Rakesh Patel
 *
 */

@Domain(value = "veterinarianlandingview", includeListeners = {ListenerType.websocket})
@Repo(value=Repo.Database.rep_none,cache=Repo.Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
@MapsTo.Type(Veterinarian.class)
@ViewRoot(layout = "home")
public class VRVeterinarianLanding {
	
//	@Page
//	private VPVeterenarians vpVeterenarians;
	
	@Page(defaultPage=true)
	private VPAddEditVeterinarian vpAddEditVeterenarian;
	
	@Model
	@Getter @Setter
	public static class VPVeterenarians  {

		@Tile(imgSrc = "resources/icons/task.svg#Layer_1", size = Tile.Size.Medium)
        private VTVeterinarians vtVeterinarians;
		
    }
	
	@Model
	@Getter @Setter
	public static class VTVeterinarians  {

		@Section
		private VSVeterinarians vsVeterinarians;
    }

	@Model
	@Getter @Setter
	public static class VSVeterinarians  {
		
		@Configs({
	         @Config(url="/p/veterinarianview/_new")
	    })
	    @Button(imgSrc = "add.svg", cssClass ="btn btn-icon green")
	    private String addVeterinarian;
		
		@MapsTo.Path(linked=false)
		@Config(url="/p/veterinarianlandingview/vpVeterenarians/vtVeterinarians/vsVeterinarians/veterinarians.m/_process?fn=_set&url=/p/veterinarian/_search?fn=example")       
		@Grid(onLoad=true, isTransient = true, pageSize = "7")
		private List<VeterinarianLineItem> veterinarians;
		
    }
}