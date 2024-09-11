package com.josh.asset_managment_system.Asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class UpdateAssetRequest {
		@NotBlank(message = "Please enter asset Name")
		private String assetName;
		@NotNull(message = "Please enter asset ID")
		private Integer assetId;

}
