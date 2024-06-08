package org.example.mosike_project.model;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadFile {
  @NotNull
  private String nameSong;
  private MultipartFile songFile;
  private MultipartFile imgFile;
}
