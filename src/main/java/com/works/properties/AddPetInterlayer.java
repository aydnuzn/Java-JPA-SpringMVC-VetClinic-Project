package com.works.properties;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AddPetInterlayer extends PetListInterlayer{
    private Integer cu_id;
}
