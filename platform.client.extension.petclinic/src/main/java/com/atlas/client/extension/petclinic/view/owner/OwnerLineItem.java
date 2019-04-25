package com.atlas.client.extension.petclinic.view.owner;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.CheckBoxGroup;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ComboBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridRowBody;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.LinkMenu;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.domain.defn.extension.EnableConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.Style;
import com.antheminc.oss.nimbus.domain.defn.extension.StyleConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.VisibleConditional;
import com.atlas.client.extension.petclinic.core.Owner;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.YesTest;

import lombok.Getter;
import lombok.Setter;

@MapsTo.Type(Owner.class)
@Getter @Setter
public class OwnerLineItem {
	
	@Config(url = "/p/owner:<!/.m/id!>/_update")
	@Config(url="<!#this!>/../../.m/_process?fn=_set&url=/p/owner/_search?fn=query")
	private String _action_onEdit;
 
	@GridColumn(hidden = true)
    @Path
    private Long id;
 
    @Label("First Name")
    @GridColumn
    @Path
    @VisibleConditional(when = "state != 'User 3'", targetPath = "/../vlmCaseItemLinks/edit")
    @EnableConditional(when = "state != 'User 4'", targetPath = "/../vlmCaseItemLinks/edit")
    private String firstName;
 
    @Label("Last Name")
    @GridColumn(applyValueStyles = true)
    @Path
    private String lastName;
    
//	@CheckBoxGroup
//	@Label("Last Name")
//	private String lastName;
// 
//    @Label("Nickname")
//    @GridColumn
//    @Path
//    private String nickname;
    
	@Label("Nickname")
	@TextBox
	@Path
	private String nickname;

    @GridColumn(hidden = true)
    @Path
    @StyleConditional(targetPath = "/../nickname", condition = {
    	@StyleConditional.Condition(when = "state == true", then = @Style(cssClass = "highlight-nickname"))
    })
    private Boolean shouldUseNickname;
    
    @Label("Status")
    @GridColumn
    @Path
    @StyleConditional(targetPath = "/../lastName", condition = {
    	@StyleConditional.Condition(when = "state == 'Inactive'", then = @Style(cssClass = "inactiveUser"))
    })
    private String status;
    
    @Label("Owner City")
    @GridColumn
    @Path("city")
    @NotNull
    private String ownerCity;
 
    @Label("Telephone")
    @NotNull
	@ComboBox()
	@Model.Param.Values(value = YesTest.class)
	@Path("telephone")
    private String telephone;   
	
//    @Label("Telephone")
//    @GridColumn
//    private Telephone telephone;   
    
    @LinkMenu
    private VLMCaseItemLinks vlmCaseItemLinks;
   
    @GridRowBody
    private ExpandedRowContent expandedRowContent;
    
//    @Button(imgSrc = "fa-edit", title = "Edit")
//	@Label(" ")
//	@Config(url = "/p/ownerview:<!/.m/id!>/_get")
//	private String edit;
//	
//    
//    @Button(imgSrc = "fa-tasks", title = "Owner Info")
//	@Label(" ")
//	@Config(url = "/p/ownerview:<!/.m/id!>/_get")
//    @Config(url = "/p/ownerview:<!/.m/id!>/_nav?pageId=vpOwnerInfo")
//    private String ownerInfo;

    @Model @Getter @Setter
    public static class ExpandedRowContent {
    	
    	@Label("Pets")
    	@Grid(onLoad = true)
    	@Path(linked = false)
		@Config(url="<!#this!>.m/_process?fn=_set&url=/p/pet/_search?fn=query&where=pet.ownerId.eq(<!/../.m/id!>)")
        private List<PetLineItemOwnerLanding> pets;
    }
    
    @MapsTo.Type(Owner.class) @Getter @Setter 
    public static class Telephone {
		@ComboBox()
		@Model.Param.Values(value = YesTest.class)
		@Path("telephone")
		private String qtelephone;
		}
    
    @Model @Getter @Setter
    public static class VLMCaseItemLinks {
        
    	@Label("Edit")
        @Link(imgSrc = "edit.png")
    	@Config(url = "/p/ownerview:<!/../id!>/_get")
    	private String edit;
     
    	@Label("Owner Info")
    	@Link(imgSrc = "task.svg")
    	@Config(url = "/p/ownerview:<!/../id!>/_get")
        @Config(url = "/p/ownerview:<!/../id!>/_nav?pageId=vpOwnerInfo")
        private String ownerInfo;
    }
}