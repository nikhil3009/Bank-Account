package com.sainik.accounts.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Accounts",
        description = "Schema holds account information"
)
public class AccountsDto {
    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;
    @NotEmpty(message = "BranchAddress cannot be empty")
    private String branchAddress;
}
