package com.workingbit.coremodule.domain.impl

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.workingbit.board.common.DbConstants
import com.workingbit.coremodule.common.EnumRules
import java.util.*

/**
 * Created by Aleksey Popryaduhin on 16:57 23/08/2017.
 */
data class Board(private val map: HashMap<String, Any?>) {

    constructor() : this(hashMapOf())

    val id: String by map
        @DynamoDBAutoGeneratedKey @DynamoDBHashKey(attributeName = DbConstants.ID) get

    var boardContainer: BoardContainer by map
        @DynamoDBTypeConvertedJson(targetType = BoardContainer::class) @DynamoDBAttribute(attributeName = "boardContainer") get

    /**
     * Is player on the black side?
     */
    val black: Boolean by map
        @DynamoDBAttribute(attributeName = "black") get

    val rules: EnumRules by map
        @DynamoDBTypeConvertedEnum @DynamoDBAttribute(attributeName = "rules") get

    /**
     * Size of one square
     */
    val squareSize: Int by map
        @DynamoDBAttribute(attributeName = "squareSize") get
}