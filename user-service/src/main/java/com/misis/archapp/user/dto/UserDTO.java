package com.misis.archapp.user.dto;

import java.io.Serializable;   // ✅ هذا السطر المهم
import java.util.UUID;

public record UserDTO(UUID id, String name, String email) implements Serializable {
}