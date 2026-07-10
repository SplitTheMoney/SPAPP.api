package ajm.spapp.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RejectRequestDTO(
        @NotBlank
        String rejectionReason

) {
}
