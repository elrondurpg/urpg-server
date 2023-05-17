package com.pokemonurpg.dto.security;

import com.pokemonurpg.dto.InputDto;

import java.util.List;

public class RoleInputDto extends InputDto {
        private String name;
        private List<RolePermissionInputDto> permissions;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<RolePermissionInputDto> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<RolePermissionInputDto> permissions) {
            this.permissions = permissions;
        }
}
