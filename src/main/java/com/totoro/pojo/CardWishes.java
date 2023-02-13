package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_card_wishes")
public class CardWishes implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;
  private Long wishesId;
  private Long cardId;
  

}
