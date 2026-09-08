package com.kamalkavin96.filemanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class RoleMappingReq {

    private Long userId;
    private Long roleId;
    
}
